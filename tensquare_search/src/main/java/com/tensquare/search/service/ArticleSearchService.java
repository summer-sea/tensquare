package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-05-29 12:24
 **/


@Service
public class ArticleSearchService {

    @Autowired
    private ArticleDao articleDao;


   /* @Autowired
    private IdWorker idWorker;*/
    /**
     * 增加文章
     * @param article
     */
    public void save(Article article){
        //默认会提供一个id所以不要提供id
        //article.setId(idWorker.nextId()+"");
        articleDao.save(article);
    }


    public Page<Article> findByTitleLike(String keywords, int page, int size)
    {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return
                articleDao.findByTitleOrContentLike(keywords,keywords,
                        pageRequest);
    }

}
