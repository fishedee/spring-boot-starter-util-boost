package com.fishedee.util_boost.autoconfig;


import com.fishedee.util_boost.ioc.IocHelper;
import com.fishedee.util_boost.ioc.SpringIocBeanFactory;
import com.fishedee.util_boost.json.JsonConfigure;
import com.fishedee.util_boost.linter.IdGeneratorLinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

@Slf4j
@Configuration
@EnableConfigurationProperties(UtilBoostProperties.class)
public class UtilBoostAutoConfiguration {
    private final AbstractApplicationContext applicationContext;

    private final UtilBoostProperties properties;

    public UtilBoostAutoConfiguration(AbstractApplicationContext applicationContext, UtilBoostProperties properties) {
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(SpringIocBeanFactory.class)
    @ConditionalOnProperty(value = "spring.util-boost.enable", havingValue = "true")
    public SpringIocBeanFactory springIocBeanFactory() {
        return new SpringIocBeanFactory();
    }

    @Bean
    @ConditionalOnMissingBean(JsonConfigure.class)
    @ConditionalOnProperty(value = "spring.util-boost.enable", havingValue = "true")
    public JsonConfigure jsonConfigure() {
        return new JsonConfigure();
    }
}