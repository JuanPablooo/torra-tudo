package com.torra.tudo.commons.spring.config;


import com.torra.tudo.commons.spring.constants.CommonsSpringConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ConditionalOnProperty(value = CommonsSpringConstants.TTD_INTERCEPTOR_CONFIG_ENABLE, havingValue = CommonsSpringConstants.TTD_HAVING_VALUE)
//@Configuration
@Component
public class HttpInterceptorConfig implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptorConfig.class);
    @Autowired
    private CustomInterceptor customInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);
        LOGGER.info("interceptor request");
        registry.addInterceptor(customInterceptor);
    }



}