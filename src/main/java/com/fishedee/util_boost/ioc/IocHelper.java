package com.fishedee.util_boost.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fish on 2021/4/28.
 */
@Slf4j
public class IocHelper {
    public interface IocBeanFactory{
        Object getBean(Class beanClass);
    }

    private static class ObjectInfo{
        private Map<Class,List<Field>> filedMap;
        private List<Class> filedClassList;
        public ObjectInfo(Class clazz){
            this.filedMap = new HashMap<Class,List<Field>>();

            while( clazz != null ){
                process(clazz);
                clazz = clazz.getSuperclass();
            }

            this.filedClassList = new ArrayList<Class>(this.filedMap.keySet());
        }

        public void process(Class clazz){
            for( Field field : clazz.getDeclaredFields()){
                Autowired annotation = field.getAnnotation(Autowired.class);
                if( annotation == null ){
                    continue;
                }
                this.put(field);
            }
        }

        public void put(Field field){
            Class fieldClass = field.getType();
            List<Field> fieldList = this.filedMap.get(fieldClass);
            if( fieldList == null ){
                fieldList = new ArrayList<Field>();
                this.filedMap.put(fieldClass,fieldList);
            }
            field.setAccessible(true);
            fieldList.add(field);
        }

        public List<Field> getFieldByClass(Class clazz){
            return this.filedMap.get(clazz);
        }

        public List<Class> getInjectTargetClass(){
            return this.filedClassList;
        }
    }

    private static class ObjectWrapper{

        private Object target;

        private ObjectInfo objectInfo;

        public ObjectWrapper(Object target,ObjectInfo objectInfo){
            this.target = target;
            this.objectInfo = objectInfo;
        }

        public void inject(Class beanClass,Object bean){
            try {
                List<Field> fieldList = this.objectInfo.getFieldByClass(beanClass);
                for (Field field : fieldList) {
                    field.set(this.target, bean);
                }
            }catch(IllegalAccessException e ){
                throw new RuntimeException(e);
            }
        }
    }

    private static IocBeanFactory beanFactory;

    private static ConcurrentHashMap<Class,ObjectInfo> objectInfoMap = new ConcurrentHashMap<>();

    public static void autoInject(Object target){
        //工厂不在的话,就不用说了
        if(beanFactory == null){
            return;
        }

        //获取对象信息
        Class clazz = target.getClass();
        ObjectInfo objectInfo = objectInfoMap.get(clazz);
        if( objectInfo == null ){
            objectInfo = new ObjectInfo(clazz);
            objectInfoMap.putIfAbsent(clazz,objectInfo);
        }

        //注入对象
        List<Class> beanClassList = objectInfo.getInjectTargetClass();
        ObjectWrapper wrapper = new ObjectWrapper(target,objectInfo);
        for( Class beanClass : beanClassList ){
            Object bean = IocHelper.getBean(beanClass);
            wrapper.inject(beanClass,bean);
        }
    }

    public static Object getBean(Class clazz){
        //spring工厂
        if(beanFactory == null) {
            return null;
        }

        //都没有
        return IocHelper.beanFactory.getBean(clazz);
    }

    public static void setBeanFactory(IocBeanFactory factory){
        IocHelper.beanFactory = factory;
    }

    public static IocBeanFactory getBeanFactory(){
        return beanFactory;
    }
}
