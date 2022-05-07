package com.torra.tudo.product.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("product")
@Log4j2
public class ProductController {

    @GetMapping
    public String test() {
        log.info("request recebida [{}]", "teste");
        System.out.println("agr foi um print ");
        System.out.println("agr foi um print2 ");
        System.out.println("agr foi um print3 ");
        return "tudo certo3";
    }
}
