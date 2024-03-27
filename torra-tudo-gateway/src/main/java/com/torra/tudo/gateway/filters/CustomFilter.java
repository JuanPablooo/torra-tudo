package com.torra.tudo.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
        System.out.println("passando no custom filter");
        log.info("passando pelo custom filter");
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filter. Suppose we can extract JWT and perform Authentication
        System.out.println("apply filter");
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("filter request uri[{}], path[{}] ", request.getURI(), request.getPath());
            System.out.println("First pre filter" + request);
            //Custom Post Filter.Suppose we can call error response handler based on error code.
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("First post filter");
            }));
        };
    }

    public static class Config {
        // Put the configuration properties
    }
}

//
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.context.annotation.Bean;
//
// import reactor.core.publisher.Mono;
//
// @SpringBootApplication
// public class APIGatewayApplication {
//
// public static void main(String[] args) {
// SpringApplication.run(APIGatewayApplication.class, args);
// }
//
// @Bean
// public GlobalFilter globalFilter() {
// return (exchange, chain) -> {
// System.out.println("First Global filter");
// return chain.filter(exchange).then(Mono.fromRunnable(() -> {
// System.out.println("Second Global filter");
// }));
// };
// }
//
// }
