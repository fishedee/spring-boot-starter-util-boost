package com.fishedee.util_boost.utils;

import com.fishedee.util_boost.ioc.IocHelper;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

import javax.validation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by fish on 2021/4/29.
 */
public class ValidatorUtil {

    private static ValidatorFactory validatorFactoryBean;

    public static ValidatorFactory init(){
        if( validatorFactoryBean == null ){
            try{
                validatorFactoryBean = (LocalValidatorFactoryBean) IocHelper.getBean(LocalValidatorFactoryBean.class);
            }catch(NoSuchBeanDefinitionException e){
                //没有bean的话就取其他的
            }
        }
        if( validatorFactoryBean == null ){
            HibernateValidatorConfiguration configure = Validation.byProvider(HibernateValidator.class).configure();
            validatorFactoryBean = configure.failFast(false).buildValidatorFactory();
        }
        return validatorFactoryBean;
    }

    public static void check(Object target){
        init();
        if( validatorFactoryBean == null ){
            return;
        }
        Set<ConstraintViolation<Object>> validateSet = validatorFactoryBean.getValidator().validate(target);
        if (!CollectionUtils.isEmpty(validateSet)) {
            Iterator<ConstraintViolation<Object>> iterator = validateSet.iterator();
            List<String> msgList = new ArrayList<>();
            while (iterator.hasNext()) {
                ConstraintViolation<?> cvl = iterator.next();
                msgList.add(cvl.getPropertyPath()+":"+cvl.getMessage());
            }
            throw new ValidationException(msgList.toString());
        }
    }
}
