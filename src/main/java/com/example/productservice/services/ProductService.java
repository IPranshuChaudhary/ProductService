package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product addProduct(Product product);

    public Product getSingleProduct(Long id) throws ProductNotFoundException;

    public List<Product> getAllProducts() throws ProductNotFoundException;

    public void deleteProduct(Long id);

    public Product updateProduct(Product product) throws ProductNotFoundException;
}
