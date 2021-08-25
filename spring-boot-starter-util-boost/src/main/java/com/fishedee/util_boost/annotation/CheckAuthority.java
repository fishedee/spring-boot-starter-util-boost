package com.fishedee.util_boost.annotation;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//该类废弃了，改用
//@PreAuthoritze("hasAuthority('readArtical')")
/**
 * Created by fish on 2021/4/28.
 */
@Deprecated
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthority {
    String[] value() default {};
}
