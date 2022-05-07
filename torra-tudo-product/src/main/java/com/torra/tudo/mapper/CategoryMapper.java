package com.torra.tudo.mapper;

import com.torra.tudo.controller.dto.CategoryDto;
import com.torra.tudo.controller.dto.CategoryInputDto;
import com.torra.tudo.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract Category toCategory(CategoryInputDto categoryInputDto);

    public abstract CategoryDto toCategoryDto(Category category);

    public abstract List<CategoryDto> toCategoriesDto(List<Category> categories);

}
