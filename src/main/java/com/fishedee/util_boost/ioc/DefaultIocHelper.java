package com.fishedee.util_boost.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.annotation.PostConstruct;

/**
 * Created by fish on 2021/4/28.
 */
@Slf4j
public class DefaultIocHelper implements BeanFactoryAware, IocHelper.IocHelperFactory {

    private BeanFactory beanFactory;

    public void setBeanFactory(BeanFactory var1) throws BeansException{
        beanFactory = var1;
    }

    @PostConstruct
    public void init(){
        IocHelper.setBeanFactory(this);
    }

    public Object getBean(Class beanClass){
        return this.beanFactory.getBean(beanClass);
    }

    public Object getBean(String beanName){
        return this.beanFactory.getBean(beanName);
    }
}
