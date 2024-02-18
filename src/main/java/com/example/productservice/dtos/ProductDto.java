package com.example.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String categoryName;
//    private String categoryDesc;

    private String description;
    private String imageUrl;
}
