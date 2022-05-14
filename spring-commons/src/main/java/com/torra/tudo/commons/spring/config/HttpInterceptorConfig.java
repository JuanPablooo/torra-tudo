package com.torra.tudo.commons.spring.config;


import com.torra.tudo.commons.spring.config.constants.CommonsSpringConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(value = CommonsSpringConstants.TTD_INTERCEPTOR_CONFIG_ENABLE, havingValue = CommonsSpringConstants.TTD_HAVING_VALUE)
@Configuration
public class HttpInterceptorConfig {
}