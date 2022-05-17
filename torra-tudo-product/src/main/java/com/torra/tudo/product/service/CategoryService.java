package com.torra.tudo.product.service;

import com.torra.tudo.product.dto.CategoryDto;
import com.torra.tudo.product.dto.CategoryInputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto createCategory(CategoryInputDto CategoryInputDto);
    CategoryDto updateCategory(CategoryInputDto CategoryInputDto, UUID id);
    CategoryDto getCategoryById(UUID id);
    Page<CategoryDto> listCategoryPageable(Pageable pageable);
    List<CategoryDto> listCategoryNoPageable();
    void deleteCategoryById(UUID id);
    
}
