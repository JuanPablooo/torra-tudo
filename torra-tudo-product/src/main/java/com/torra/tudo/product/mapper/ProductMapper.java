package com.torra.tudo.product.mapper;

import com.torra.tudo.product.dto.ProductDto;
import com.torra.tudo.product.dto.ProductInputDto;
import com.torra.tudo.product.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    //TODO ignore category ids
    public abstract Product toProduct(ProductInputDto ProductInputDto);

    public abstract ProductDto toProductDto(Product Product);

    public abstract List<ProductDto> toProductsDto(List<Product> products);
}
