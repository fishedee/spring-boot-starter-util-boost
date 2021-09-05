package com.fishedee.util_boost.ioc;

//该IocFactory与DeafultIocHelper并不相同，虽然工作内容相似
//DefaultIocHelper不仅提供bean查找功能，还承担着注入IocHelper实现的职责，它是不能被mock，或者被替换的
//IocFactory是仅仅提供bean查找功能，不承担IocHelper注入实现的职责，它是允许被mock，我们所有的应用代码都应该使用IocFactory
public class IocFactory implements IocHelper.IocHelperFactory{

    @Override
    public Object getBean(Class beanClass){
        return IocHelper.getBean(beanClass);
    }

    @Override
    public Object getBean(String beanName){
        return IocHelper.getBean(beanName);
    }
}
