package com.torra.tudo.product.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@Data
public class ProductProperties {
    @Value("${url.test}")
    private String urlTest;
}
