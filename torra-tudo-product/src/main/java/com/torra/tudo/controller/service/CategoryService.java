package com.torra.tudo.controller.service;

import com.torra.tudo.controller.dto.CategoryDto;
import com.torra.tudo.controller.dto.CategoryInputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDto createCategory(CategoryInputDto CategoryInputDto);
    CategoryDto updateCategory(CategoryInputDto CategoryInputDto);
    CategoryDto getCategoryById(UUID id);
    Page<CategoryDto> listCategoryPageable(Pageable pageable);
    List<CategoryDto> listCategoryNoPageable();
    void deleteCategoryById(UUID id);
    
}
