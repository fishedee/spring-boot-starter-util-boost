package com.fishedee.util_boost.ioc;

import java.util.HashMap;
import java.util.Map;

public class MemoryBeanFactory implements IocHelper.IocBeanFactory {
    private Map<Class, Object> mapObjectInfo;

    public MemoryBeanFactory() {
        mapObjectInfo = new HashMap<>();
    }

    public void inject(Class clazz, Object target) {
        Map<Class, Object> result = mapObjectInfo;
        if (result == null) {
            result = new HashMap<>();
        }
        result.put(clazz, target);
    }

    public Object getBean(Class beanClass) {
        Map<Class, Object> result = mapObjectInfo;
        if (result == null) {
            return null;
        }
        return result.get(beanClass);
    }
}
