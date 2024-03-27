package com.torra.commons.data.property;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpHeaders;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.config")
@Getter
@Setter
public class JwtConfiguration {
    private String loginUrl = "/login/**";
    @NestedConfigurationProperty
    private Header header = new Header();
    private int expiration = 3600;
    private String privateKey = "9fVXLqIJwZGxn0EnobqsK3cSFFVYWhXj";
    private String type = "encrypted";


    @Getter
    @Setter
    public static class Header {
        private String name = HttpHeaders.AUTHORIZATION;
        private String prefix = "Bearer ";

    }
}
