package com.torra.tudo.product.service;

import com.torra.tudo.product.dto.CategoryDto;
import com.torra.tudo.product.dto.CategoryInputDto;
import com.torra.tudo.product.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CategoryService {
    CategoryDto createCategory(CategoryInputDto categoryInputDto);
    CategoryDto updateCategory(CategoryInputDto categoryInputDto, UUID id) throws ClassNotFoundException;
    CategoryDto getCategoryById(UUID id) throws ClassNotFoundException;
    Page<CategoryDto> listCategoryPageable(Pageable pageable);
    List<CategoryDto> listCategoryNoPageable();
    void deleteCategoryById(UUID id) throws ClassNotFoundException;

    Set<Category> findCategoriesByIds(List<UUID> categoriesId);
    
}
