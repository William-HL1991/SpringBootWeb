package com.example.springbootweb.biz.impl;

import com.example.springbootweb.biz.service.ImgUploadService;
import com.example.springbootweb.biz.execption.ServiceException;
import com.example.springbootweb.common.error.SpringBootWebErrors;
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
public class ImgUplodServiceImpl implements ImgUploadService {
    private static final Logger logger = LoggerFactory.getLogger(ImgUplodServiceImpl.class.getName());

    @Autowired
    private ImgMapper imgMapper;

    @Override
    public boolean insert(Img img) {
        if (Objects.isNull(img)) {
            new ServiceException(SpringBootWebErrors.CONDITION_IS_NOT_EXIST);
        }
        try {
            int result = imgMapper.insert(img);
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
    }
}
