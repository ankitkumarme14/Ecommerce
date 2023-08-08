package com.productService.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long productId;
    private String name;
    private Double price;
    private int quantityInStock;
}
