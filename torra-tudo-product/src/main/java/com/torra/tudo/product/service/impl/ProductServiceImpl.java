package com.torra.tudo.product.service.impl;

import com.torra.tudo.product.dto.ProductDto;
import com.torra.tudo.product.dto.ProductInputDto;
import com.torra.tudo.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    @Override
    public ProductDto createProduct(ProductInputDto productInputDto) {

        return null;
    }

    @Override
    public ProductDto updateProduct(ProductInputDto productInputDto) {
        return null;
    }

    @Override
    public ProductDto getProductById(UUID id) {
        return null;
    }

    @Override
    public Page<ProductDto> listProductPageable(Pageable pageable) {
        return null;
    }

    @Override
    public List<ProductDto> listProductNoPageable() {
        return null;
    }

    @Override
    public void deleteProductById(UUID id) {

    }
}
