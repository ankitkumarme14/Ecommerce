package com.productService.service;

import com.productService.payload.ProductDto;

import java.util.List;

public interface ProductService {
        public ProductDto createProduct(ProductDto productDto);

        public List<ProductDto> getProducts();

        public ProductDto getByProductIds(Long productId);

        public ProductDto updateById(Long productId, ProductDto productDto);

        public void deleteProductById(Long productId);
}
