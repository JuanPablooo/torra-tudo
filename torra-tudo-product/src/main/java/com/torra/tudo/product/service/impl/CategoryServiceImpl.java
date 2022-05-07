package com.torra.tudo.product.service.impl;

import com.torra.tudo.product.dto.CategoryDto;
import com.torra.tudo.product.dto.CategoryInputDto;
import com.torra.tudo.product.service.CategoryService;
import com.torra.tudo.product.mapper.CategoryMapper;
import com.torra.tudo.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryInputDto CategoryInputDto) {

        return null;
    }

    @Override
    public CategoryDto updateCategory(CategoryInputDto CategoryInputDto) {
        return null;
    }

    @Override
    public CategoryDto getCategoryById(UUID id) {
        return null;
    }

    @Override
    public Page<CategoryDto> listCategoryPageable(Pageable pageable) {
        return null;
    }

    @Override
    public List<CategoryDto> listCategoryNoPageable() {
        return null;
    }

    @Override
    public void deleteCategoryById(UUID id) {

    }
}
