package com.torra.tudo.product.service.impl;

import com.torra.tudo.product.dto.CategoryDto;
import com.torra.tudo.product.dto.CategoryInputDto;
import com.torra.tudo.product.entity.Category;
import com.torra.tudo.product.service.CategoryService;
import com.torra.tudo.product.mapper.CategoryMapper;
import com.torra.tudo.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public CategoryDto createCategory(CategoryInputDto categoryInputDto) {
        Category categoryToBeCreate = categoryMapper.toCategory(categoryInputDto);
        Category categorySave = categoryRepository.save(categoryToBeCreate);
        return categoryMapper.toCategoryDto(categorySave);
    }

    @Override
    public CategoryDto updateCategory(CategoryInputDto categoryInputDto, UUID id) {
        Category categoryById = findOrThrowById(id);
        Category categoryToBeUpdate = categoryMapper.toCategory(categoryInputDto);
        categoryToBeUpdate.setId(categoryById.getId());
        Category save = categoryRepository.save(categoryToBeUpdate);
        return categoryMapper.toCategoryDto(save);
    }

    @Override
    public CategoryDto getCategoryById(UUID id) {
        Category categoryById = findOrThrowById(id);
        return categoryMapper.toCategoryDto(categoryById);
    }

    @Override
    public Page<CategoryDto> listCategoryPageable(Pageable pageable) {
        Page<Category> categoriesFound = categoryRepository.findAll(pageable);
        List<CategoryDto> categoryDtos = categoryMapper.toCategoriesDto(categoriesFound.getContent());
        return new PageImpl<>(categoryDtos, pageable, categoriesFound.getTotalElements());
    }

    @Override
    public List<CategoryDto> listCategoryNoPageable() {
        List<Category> categoriesFound = categoryRepository.findAll();
        return categoryMapper.toCategoriesDto(categoriesFound);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        findOrThrowById(id);
        deleteCategoryById(id);
    }
    private Category findOrThrowById(UUID id){
        return categoryRepository.findById(id).orElseThrow();
    }
}
