package com.example.springbootweb.biz.impl;

import com.example.springbootweb.biz.service.ImgService;
import com.example.springbootweb.biz.execption.ServiceException;
import com.example.springbootweb.common.error.SpringBootWebErrors;
import com.example.springbootweb.common.redis.CacheTime;
import com.example.springbootweb.common.redis.RedisClient;
import com.example.springbootweb.dao.mapper.ImgMapper;
import com.example.springbootweb.dao.module.Img;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author william
 * @date 2020/4/21
 */
@Service
public class ImgServiceImpl implements ImgService {
    private static final Logger logger = LoggerFactory.getLogger(ImgServiceImpl.class.getName());

    @Autowired
    private ImgMapper imgMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public Img findByName(String name) {
        Img img = imgMapper.selectByName(name);
        if (Objects.isNull(img)) {
            throw new ServiceException(SpringBootWebErrors.CONDITION_IS_NOT_EXIST);
        }
        redisClient.set("name:" + name, img, CacheTime.CACHE_EXP_FIVE_MINUTES);
        return img;
    }

}
