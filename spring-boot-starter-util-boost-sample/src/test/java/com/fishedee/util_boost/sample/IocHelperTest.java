package com.fishedee.util_boost.sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fishedee.util_boost.autoconfig.UtilBoostAutoConfiguration;
import com.fishedee.util_boost.ioc.IocFactory;
import com.fishedee.util_boost.ioc.IocHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.persistence.Transient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@DataJpaTest
@Import({UtilBoostAutoConfiguration.class})
@Slf4j
public class IocHelperTest {
    @Slf4j
    public static class MyKK{
        @JsonIgnore
        @Transient
        @Autowired
        private IocFactory iocFactory;

        {
            IocHelper.autoInject(this);
        }

        public String get(String beanName){
            return (String)iocFactory.getBean(beanName);
        }
    }

    public static class MyJJ{

    }
    @Slf4j
    public static class MyMM{
        @JsonIgnore
        @Transient
        @Autowired
        private MyJJ jj;

        {
            IocHelper.autoInject(this);
        }
    }

    @MockBean
    private IocFactory iocFactory;

    @Test
    public void go1(){
        when(iocFactory.getBean("aa")).thenReturn("1");
        when(iocFactory.getBean("bb")).thenReturn("2");

        assertEquals(iocFactory.getBean("aa"),"1");
        assertEquals(iocFactory.getBean("bb"),"2");

        MyKK kk = new MyKK();
        assertEquals(kk.get("aa"),"1");
        assertEquals(kk.get("bb"),"2");

    }

    @Test
    public void go2(){
        NoSuchBeanDefinitionException e = assertThrows(NoSuchBeanDefinitionException.class,()->{
            MyMM kk = new MyMM();
        });
        assertEquals(e.getBeanType(),MyJJ.class);
    }

}
