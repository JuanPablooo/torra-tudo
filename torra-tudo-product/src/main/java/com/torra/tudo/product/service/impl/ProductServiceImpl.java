package com.torra.tudo.product.service.impl;

import com.torra.tudo.product.dto.ProductDto;
import com.torra.tudo.product.dto.ProductInputDto;
import com.torra.tudo.product.entity.Category;
import com.torra.tudo.product.entity.Product;
import com.torra.tudo.product.mapper.ProductMapper;
import com.torra.tudo.product.repository.ProductRepository;
import com.torra.tudo.product.service.CategoryService;
import com.torra.tudo.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    @Autowired
    public ProductServiceImpl (ProductRepository productRepository, ProductMapper productMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ProductDto createProduct(ProductInputDto productInputDto) {
        log.info("starter create product [{}]", productInputDto.getName());
        Product productToBeSave = productMapper.toProduct(productInputDto);
        Set<Category> categoriesOfProduct = categoryService.findCategoriesByIds(productInputDto.getCategoryIds());
        productToBeSave.setCategories(categoriesOfProduct);
        return productMapper.toProductDto(productRepository.save(productToBeSave));
    }

    @Override
    public ProductDto updateProduct(ProductInputDto productInputDto, UUID productId) throws ClassNotFoundException {
        log.info("starter update product [{}]", productId);
        Product productToBeUpdate = findProductByIdOrThrow(productId);
        Product replaceProduct = productMapper.toProduct(productInputDto);
        replaceProduct.setId(productToBeUpdate.getId());
        Set<Category> categoriesOfProduct = categoryService.findCategoriesByIds(productInputDto.getCategoryIds());
        replaceProduct.setCategories(categoriesOfProduct);
        productRepository.save(replaceProduct);
        return productMapper.toProductDto(replaceProduct);
    }

    @Override
    public ProductDto getProductById(UUID id) throws ClassNotFoundException {
        Product productFound = findProductByIdOrThrow(id);
        return productMapper.toProductDto(productFound);
    }

    @Override
    public Page<ProductDto> listProductPageable(Pageable pageable) {
        log.info("starter list pageable products [{}]", pageable);
        Page<Product> pageProduct = productRepository.findAll(pageable);
        List<ProductDto> productsDto = productMapper.toProductsDto(pageProduct.getContent());
        return new PageImpl<>(productsDto, pageable, pageProduct.getTotalElements());
    }

    @Override
    public List<ProductDto> listProductNoPageable() {
        log.info("starter list all products");
        List<Product> products = productRepository.findAll();
        return productMapper.toProductsDto(products);
    }

    @Override
    public void deleteProductById(UUID productId) throws ClassNotFoundException {
        findProductByIdOrThrow(productId);
        productRepository.deleteById(productId);
    }
    private Product findProductByIdOrThrow(UUID productId) throws ClassNotFoundException {
        log.info("starter find product by id[{}]", productId);
        return productRepository.findById(productId).orElseThrow(() -> {
            log.warn("product not found by id [{}]", productId);
            return new ClassNotFoundException("product not found by id:" + productId);
        });
    }
}
