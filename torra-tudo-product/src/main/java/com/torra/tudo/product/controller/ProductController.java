package com.torra.tudo.product.controller;

import com.torra.tudo.product.config.ProductProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@Log4j2
public class ProductController {
    @Autowired
    public ProductController(ProductProperties properties) {
        this.properties = properties;
    }

    private final ProductProperties properties;

    @GetMapping
    public String test() {
        log.info("request recebida [{}]", "teste");
        System.out.println("agr foi um print +:"+ properties.getUrlTest());
        System.out.println("agr foi um print2 ");
        System.out.println("agr foi um print3 ");
        return properties.getUrlTest();
    }
}
