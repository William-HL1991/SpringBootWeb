package com.example.springbootweb.dao.mapper;

import com.example.springbootweb.dao.module.Img;

import java.util.List;

import com.example.springbootweb.dao.param.ImgConditionBuilder;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgMapper {

    /**
     * 插入数据
     * @param record ImgBean
     * @return
     */
    int insert(Img record);

    /**
     * 插入（匹配有值的字段）
     *
     * @param record Img
     * @return int
     */
    int insertSelective(Img record);

    /**
     * 动态条件查询（匹配有值的字段）
     *
     * @param params 筛选条件
     * @return List<Img>
     */
    List<Img> selectByCondition(ImgConditionBuilder params);

    /**
     * 根据图片名查询
     * @param name  图片名
     * @return      Img
     */
    Img selectByName(String name);


}