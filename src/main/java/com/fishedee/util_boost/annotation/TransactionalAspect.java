package com.fishedee.util_boost.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by fish on 2021/4/28.
 */
@Aspect
public class TransactionalAspect {
    @Around("@annotation(com.fishedee.util_boost.annotation.TransactionalForWrite)")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        //互斥锁,避免写请求并发产生的问题
        synchronized (this) {
            return point.proceed();
        }
    }
}
