package com.torra.tudo.product.mapper;

import com.torra.tudo.product.dto.CategoryDto;
import com.torra.tudo.product.dto.CategoryInputDto;
import com.torra.tudo.product.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract Category toCategory(CategoryInputDto categoryInputDto);

    public abstract CategoryDto toCategoryDto(Category category);

    public abstract List<CategoryDto> toCategoriesDto(List<Category> categories);

}
