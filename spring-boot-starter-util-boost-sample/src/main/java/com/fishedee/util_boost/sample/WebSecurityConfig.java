package com.fishedee.util_boost.sample;

import com.fishedee.security_boost.SecurityBoostConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
public class WebSecurityConfig extends SecurityBoostConfiguration {
    @Override
    protected void configureAuthorizeRequests(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("*").permitAll();
    }
}
