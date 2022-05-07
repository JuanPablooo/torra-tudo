package com.torra.tudo.product.service;

import com.torra.tudo.product.dto.ProductDto;
import com.torra.tudo.product.dto.ProductInputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto createProduct(ProductInputDto productInputDto);
    ProductDto updateProduct(ProductInputDto productInputDto);
    ProductDto getProductById(UUID id);
    Page<ProductDto> listProductPageable(Pageable pageable);
    List<ProductDto> listProductNoPageable();
    void deleteProductById(UUID id);

}
