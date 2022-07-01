package com.torra.tudo.product.service.impl;

import com.torra.tudo.product.dto.CategoryDto;
import com.torra.tudo.product.dto.CategoryInputDto;
import com.torra.tudo.product.entity.Category;
import com.torra.tudo.product.service.CategoryService;
import com.torra.tudo.product.mapper.CategoryMapper;
import com.torra.tudo.product.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
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
        log.info("starter create category [{}]", categoryInputDto.getName());
        Category categoryToBeCreate = categoryMapper.toCategory(categoryInputDto);
        Category categorySave = categoryRepository.save(categoryToBeCreate);
        return categoryMapper.toCategoryDto(categorySave);
    }

    @Override
    public CategoryDto updateCategory(CategoryInputDto categoryInputDto, UUID categoryId) throws ClassNotFoundException {
        log.info("start update category by categoryId [{}]", categoryId);
        Category categoryById = findOrThrowById(categoryId);
        Category categoryToBeUpdate = categoryMapper.toCategory(categoryInputDto);
        categoryToBeUpdate.setId(categoryById.getId());
        Category save = categoryRepository.save(categoryToBeUpdate);
        return categoryMapper.toCategoryDto(save);
    }

    @Override
    public CategoryDto getCategoryById(UUID id) throws ClassNotFoundException {
        Category categoryById = findOrThrowById(id);
        return categoryMapper.toCategoryDto(categoryById);
    }

    @Override
    public Page<CategoryDto> listCategoryPageable(Pageable pageable) {
        log.info("starter list category pageable [{}]", pageable);
        Page<Category> categoriesFound = categoryRepository.findAll(pageable);
        List<CategoryDto> categoryDtos = categoryMapper.toCategoriesDto(categoriesFound.getContent());
        return new PageImpl<>(categoryDtos, pageable, categoriesFound.getTotalElements());
    }

    @Override
    public List<CategoryDto> listCategoryNoPageable() {
        log.info("starter list all category");
        List<Category> categoriesFound = categoryRepository.findAll();
        return categoryMapper.toCategoriesDto(categoriesFound);
    }

    @Override
    public Set<Category> findCategoriesByIds(List<UUID> categoriesIds) {
        log.info("find categories by ids [{}]", categoriesIds);
        List<Category> allById = categoryRepository.findAllById(categoriesIds);
       return Set.copyOf(allById);
    }

    @Override
    public void deleteCategoryById(UUID categoryId) throws ClassNotFoundException {
        log.info("starter delete category by categoryId [{}]", categoryId);
        findOrThrowById(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    private Category findOrThrowById(UUID categoryId) throws ClassNotFoundException {
        log.info("starter find category by categoryId [{}]", categoryId);
        return categoryRepository.findById(categoryId).orElseThrow(() -> {
            log.warn("not found category by categoryId [{}]", categoryId);
            return new ClassNotFoundException("category not found by categoryId "+ categoryId);
        });
    }
}
