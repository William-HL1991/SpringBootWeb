package com.example.springbootweb.biz.service;

import com.example.springbootweb.dao.module.Img;

/**
 * @author william
 * @date 2020/4/21
 */
public interface ImgService {

    Img findByName(String name);

}
