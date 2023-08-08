package com.productService.controller;

import com.productService.payload.ProductDto;
import com.productService.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService= productService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProductDetails(@RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.createProduct(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getByProductId(@PathVariable Long productId){
        ProductDto productDto = productService.getByProductIds(productId);
        return new ResponseEntity<>(productDto, HttpStatus.FOUND);
    }

    @PutMapping("product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.updateById(productId, productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }


    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<>("Product is Deleted SuccessFully", HttpStatus.OK);
    }
}
