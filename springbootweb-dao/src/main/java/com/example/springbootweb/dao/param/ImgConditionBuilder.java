package com.example.springbootweb.dao.param;

import lombok.Builder;

import java.util.List;

/**
 * @author william
 * @date 2020/4/21
 */
@Builder
public class ImgConditionBuilder {

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * id的List条件
     */
    private List<Integer> idList;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * case url
     */
    private String url;

    /**
     * caseid
     */
    private String caseId;

    /**
     * newcaseid
     */
    private String newCaseId;
}

