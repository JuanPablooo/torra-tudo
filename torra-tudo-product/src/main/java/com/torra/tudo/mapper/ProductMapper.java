package com.torra.tudo.mapper;

import com.torra.tudo.controller.dto.ProductDto;
import com.torra.tudo.controller.dto.ProductInputDto;
import com.torra.tudo.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public abstract Product toProduct(ProductInputDto ProductInputDto);

    public abstract ProductDto toProductDto(Product Product);

    public abstract List<ProductDto> toProductsDto(List<Product> products);
}
