package com.example.springbootweb.dao.common;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author william
 * @date 2020/7/23
 */
public class DSPointcut {
    @Pointcut("execution(public * com.example.springbootweb.dao.mapper.*.*(..))")
    public void selectorDSPointcut() {

    }

}
