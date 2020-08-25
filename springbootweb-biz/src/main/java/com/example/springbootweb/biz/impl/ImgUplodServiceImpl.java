package com.example.springbootweb.biz.impl;

import com.example.springbootweb.biz.execption.BusinessException;
import com.example.springbootweb.biz.service.ImgUploadService;
import com.example.springbootweb.common.error.SpringBootWebErrors;
import com.example.springbootweb.dao.mapper.ImgMapper;
import com.example.springbootweb.dao.module.Img;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author william
 * @date 2020/4/21
 */
@Slf4j
@Service
public class ImgUplodServiceImpl implements ImgUploadService {

    @Autowired
    private ImgMapper imgMapper;

    @Override
    public boolean insert(Img img) {
        if (Objects.isNull(img)) {
            new BusinessException(SpringBootWebErrors.CONDITION_IS_NOT_EXIST);
        }
        try {
            int result = imgMapper.insert(img);
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
            return false;
        }
    }
}
