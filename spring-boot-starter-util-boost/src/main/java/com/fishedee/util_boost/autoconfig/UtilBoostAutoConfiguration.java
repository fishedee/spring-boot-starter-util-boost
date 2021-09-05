package com.fishedee.util_boost.autoconfig;


import com.fishedee.util_boost.ioc.DefaultIocHelper;
import com.fishedee.util_boost.ioc.IocFactory;
import com.fishedee.util_boost.json.JsonConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.AbstractApplicationContext;

@Slf4j
@Configuration
@EnableConfigurationProperties(UtilBoostProperties.class)
@Import( JacksonAutoConfiguration.class)
public class UtilBoostAutoConfiguration {
    private final AbstractApplicationContext applicationContext;

    private final UtilBoostProperties properties;

    public UtilBoostAutoConfiguration(AbstractApplicationContext applicationContext, UtilBoostProperties properties) {
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(JsonConfigure.class)
    @ConditionalOnProperty(value = "spring.util-boost.enable", havingValue = "true")
    public JsonConfigure jsonConfigure() {
        return new JsonConfigure();
    }

    @Bean
    @ConditionalOnMissingBean(DefaultIocHelper.class)
    @ConditionalOnProperty(value = "spring.util-boost.enable", havingValue = "true")
    public DefaultIocHelper defaultIocHelper(){
        return new DefaultIocHelper();
    }

    @Bean
    @ConditionalOnMissingBean(IocFactory.class)
    @ConditionalOnProperty(value = "spring.util-boost.enable", havingValue = "true")
    public IocFactory iocFactory(){
        return new IocFactory();
    }
    //这里不能将IdGeneratorLinter加入bean，因为@EnableJPALint调用的时机比较早，这个时候的IdGeneratorLinter的bean还没来得及初始化，只能由用户手动注册bean
    /*
    @Bean
    @ConditionalOnMissingBean(IdGeneratorLinter.class)
    @ConditionalOnProperty(value = "spring.util-boost.enable", havingValue = "true")
    public IdGeneratorLinter idGeneratorLinter() {
        return new IdGeneratorLinter();
    }
     */
}