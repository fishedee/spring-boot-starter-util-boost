package com.fishedee.util_boost.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="spring.util-boost")
public class UtilBoostProperties {
    private boolean enable;
}
