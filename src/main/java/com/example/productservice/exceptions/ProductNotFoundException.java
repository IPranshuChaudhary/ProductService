package com.example.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception{
    private String error;

    public ProductNotFoundException(String error) {
        this.error = error;
    }
}
