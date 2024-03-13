package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@RestController
//@RequestMapping("/products")
public class FakeStoreProductController {

    private ProductService productService;

    FakeStoreProductController(ProductService productService){

        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getSingleProduct(id);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortBy}")
    public List<Product> getAllProduct(@PathVariable int pageNumber,@PathVariable int pageSize,
                                       @PathVariable String sortBy) throws ProductNotFoundException {
        return productService.getAllProducts(pageNumber, pageSize, sortBy);
    }






}
