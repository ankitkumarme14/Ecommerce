package com.productService.service.Impl;

import com.productService.entity.Product;
import com.productService.exception.ResourceNotFoundException;
import com.productService.payload.ProductDto;
import com.productService.repository.ProductRepository;
import com.productService.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    public ProductServiceImpl( ProductRepository productRepository, ModelMapper modelMapper){
        this.productRepository=productRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product productEntity = mapToEntity(productDto);
        Product saved = productRepository.save(productEntity);
        ProductDto productDto1 = mapToDto(saved);
        return productDto1;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> dtoList = productList.stream().map(f -> mapToDto(f)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public ProductDto getByProductIds(Long productId) {
//        Optional<Product> byId = productRepository.findById(productId);
//        Product product = byId.get();
//        ProductDto productDto = mapToDto(product);
//        return productDto;

        Optional<Product> byId = productRepository.findById(productId);
        if (byId.isPresent()){
            Product product = byId.get();
            ProductDto productDto = mapToDto(product);
            return productDto;
        }else {
            throw new ResourceNotFoundException("Product is not available of this Id." );
        }
    }

    @Override
    public ProductDto updateById(Long productId, ProductDto productDto) {
        Optional<Product> byId = productRepository.findById(productId);
        if(byId.isPresent()){
            Product mapToEntity = mapToEntity(productDto);
            Product saved = productRepository.save(mapToEntity);
            ProductDto productDto1 = mapToDto(saved);
            return productDto1;
        }else {
            throw new ResourceNotFoundException("Product is not available..");
        }
//
//        return productDto;
//            Product mapToEntity = mapToEntity(productDto);
//            Product saved = productRepository.save(mapToEntity);
//            ProductDto productDto1 = mapToDto(saved);
//            return productDto1;
    }

    @Override
    public void deleteProductById(Long productId) {
        Optional<Product> byId = productRepository.findById(productId);
        if(byId.isPresent()){
            productRepository.deleteById(productId);
        }else {
            throw new ResourceNotFoundException("Product is not available..");
        }
    }

    public Product mapToEntity(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        return product;
    }

    public ProductDto mapToDto(Product product){
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

}
