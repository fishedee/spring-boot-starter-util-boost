package com.fishedee.util_boost.linter;

import com.fishedee.id_generator.IdGeneratorKey;
import com.fishedee.id_generator.NoIdGeneratorKey;
import com.fishedee.jpa_boost.lint.Assert;
import com.fishedee.jpa_boost.lint.JPALinter;
import com.fishedee.jpa_boost.lint.JPAWrapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class IdGeneratorLinter implements JPALinter {

    private static Set<String> idGeneratorKeySet = new HashSet<>();

    @Override
    public void process(JPAWrapper bean){

        log.info("lint pre {} {}",bean,bean.hasEntityAnnotation());
        if( bean.hasEntityAnnotation() == false ){
            return;
        }
        log.info("lint process ",bean);
        try{
            this.check(bean.getClassName());
        }catch(Exception e ){
            throw new RuntimeException(e);
        }
    }

    private void check(String className)throws Exception{
        Assert assertUtil = new Assert(className);
        Class clazz = Class.forName(className);
        NoIdGeneratorKey annotation2 = (NoIdGeneratorKey) clazz.getAnnotation(NoIdGeneratorKey.class);
        if( annotation2 != null ){
            return;
        }

        IdGeneratorKey annotation = (IdGeneratorKey) clazz.getAnnotation(IdGeneratorKey.class);
        assertUtil.assertNotNull("shouldHaveIdGeneratorKey",annotation);
        String key = annotation.value().trim();
        assertUtil.assertTrue("key should not empty!",key.length()!=0);
        assertUtil.assertFalse("duplicate @IdGeneratorKey",idGeneratorKeySet.contains(key));

        idGeneratorKeySet.add(key);
    }

}
