package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    ProductServiceImplementation(ProductRepository productRepository,
                                 CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.
                findByName(product.getCategory().getName());

        if (categoryOptional.isPresent()){
            product.setCategory(categoryOptional.get());
        }

        return productRepository.save(product);
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+id+" doesn't exist");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        Optional<Product []> optionalProducts = productRepository.getAllProducts();

        if (optionalProducts.isEmpty()){
            throw new ProductNotFoundException("No Products Found");
        }

        List<Product> productList = new ArrayList<>();
        for (Product product: optionalProducts.get()){
            productList.add(product);
        }
        return productList;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {

        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+product.getId()+" doesn't exist");

        }

        Product product1 = new Product();
        product1.setId(product.getId());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        product1.setTitle(product.getTitle());
        product1.setCategory(product.getCategory());

        return productRepository.save(product1);
    }
}