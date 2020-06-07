package com.tensquare.base.dao;


import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 *
 * JpaRepository提供了基本的增删改查
 * JpaSpecificationExecutor用于做复杂的条件查询
 *
 */
public interface LabelDao extends
        //string 是主键的类型
        JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {


}
