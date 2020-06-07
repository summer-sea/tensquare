package com.tensquare.base.service;

import util.IdWorker;
import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-21 11:05
 **/

@Service
@Transactional
public class LabelService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private LabelDao labelDao;

    public List<Label> findAll(){
        return  labelDao.findAll();
    }


    public Label findById(String id){
        return labelDao.findById(id).get();
    }


    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        //label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }


    public void deleteById(String id){
        labelDao.deleteById(id);
    }


    /**
     *
     * @param label
     * @return
     * 条件查询
     */
    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
           //root指跟对象也就是要把条件封装到那个对象中，where 类名=label.getid
            //query 封装的都是查询关键字，比如group by order by；
            //cb 用来封装条件对象的

            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?>
                    query, CriteriaBuilder cb) {
                //new 一个集合来存放所有的条件
                List<Predicate> list =new ArrayList<>();

                if(label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate predicate =
                            //相对于where labelname like "%小明%"
                            cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");

                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals(label.getState())){
                    Predicate predicate =
                            //相对于where state = 1"
                            cb.like(root.get("state").as(String.class),label.getState());

                    list.add(predicate);
                }

                //new 一个数组作为最终返回值条件
                Predicate[] parr = new Predicate[list.size()];

                parr=list.toArray(parr);

                return cb.and(parr); //where labelname like "小明" and state =1
            }
        });
    }

    /**
     * 分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    public Page<Label> pageQuery(Label label, int page, int size) {
        //疯转一个分页对象
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            //root指跟对象也就是要把条件封装到那个对象中，where 类名=label.getid
            //query 封装的都是查询关键字，比如group by order by；
            //cb 用来封装条件对象的

            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?>
                    query, CriteriaBuilder cb) {
                //new 一个集合来存放所有的条件
                List<Predicate> list =new ArrayList<>();

                if(label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate predicate =
                            //相对于where labelname like "%小明%"
                            cb.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");

                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals(label.getState())){
                    Predicate predicate =
                            //相对于where state = 1"
                            cb.like(root.get("state").as(String.class),label.getState());

                    list.add(predicate);
                }

                //new 一个数组作为最终返回值条件
                Predicate[] parr = new Predicate[list.size()];

                parr=list.toArray(parr);

                return cb.and(parr); //where labelname like "小明" and state =1
            }
        },pageable);
    }
}
