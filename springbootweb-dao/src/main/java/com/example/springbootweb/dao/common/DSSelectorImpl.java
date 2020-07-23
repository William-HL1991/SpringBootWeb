package com.example.springbootweb.dao.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author william
 * @date 2020/7/23
 */
@Component
@Aspect
public class DSSelectorImpl {

    @Before("com.example.springbootweb.dao.common.DSPointcut.selectorDSPointcut()")
    public void changeDS(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DSSelector selector = method.getAnnotation(DSSelector.class);
        if (selector == null) return;
        MultipleDataSourceHelper.set(selector.value());
    }
}
