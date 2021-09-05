package com.fishedee.util_boost.sample;

import com.fishedee.jpa_boost.lint.EnableJPALint;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableJPALint(
        allowIdHaveGeneratedValue = true,
        extraLinters = {
                MyLinter.class,
        }
)
public class MainConfig {
}
