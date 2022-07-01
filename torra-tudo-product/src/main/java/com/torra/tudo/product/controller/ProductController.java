package com.torra.tudo.product.controller;

import com.torra.tudo.product.config.ProductProperties;
import com.torra.tudo.product.dto.ProductDto;
import com.torra.tudo.product.dto.ProductInputDto;
import com.torra.tudo.product.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
@Log4j2
public class ProductController {
    @Autowired
    public ProductController(ProductProperties properties, ProductService productService) {
        this.properties = properties;
        this.productService = productService;
    }

    private final ProductProperties properties;
    private final ProductService productService;
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductInputDto productInputDto) {
        return productService.createProduct(productInputDto);
    }
    @GetMapping("/{productId}")
    public ProductDto findById(@PathVariable("productId") UUID productId) {
        return productService.getProductById(productId);
    }
    @GetMapping
    public Page<ProductDto> listProductPageable(Pageable pageable) {
        return productService.listProductPageable(pageable);
    }
    @GetMapping("/all")
    public List<ProductDto> listAllProduct() {
        return productService.listProductNoPageable();
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable("productId") UUID productId, @RequestBody ProductInputDto productInputDto) {
        return productService.updateProduct(productInputDto, productId);
    }

    @GetMapping("/teste")
    public String test() {
        log.info("request recebida [{}]", "teste");
        System.out.println("agr foi um print +:"+ properties.getUrlTest());
        System.out.println("agr foi um print2 ");
        System.out.println("agr foi um print3 ");
        return properties.getUrlTest();
    }

}
