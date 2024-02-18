package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){

        Product product = productService.addProduct(getProductFromDto(productDto));

        return new ResponseEntity<>(getProductDtoFromProduct(product), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product = productService.getSingleProduct(id);
        return new ResponseEntity<>(getProductDtoFromProduct(product),HttpStatus.OK);
    }

    @GetMapping()
    public List<ProductDto> getAllProducts() throws ProductNotFoundException {

        List<Product> products= productService.getAllProducts();;

        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product product: products){
            productDtoList.add(getProductDtoFromProduct(product));
        }
        return productDtoList;
    }

    @PostMapping("/update")
    public ProductDto updateProducts(@RequestBody Product product) throws ProductNotFoundException {
        return getProductDtoFromProduct(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public void deleteProducts(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }


    public Product getProductFromDto(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());

        product.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setName(productDto.getCategoryName());
        product.setCategory(category);

        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());

        return product;
    }

    public ProductDto getProductDtoFromProduct(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(productDto.getId());
        productDto.setTitle(product.getTitle());

        productDto.setPrice(product.getPrice());

        productDto.setCategoryName(product.getCategory().getName());

        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());

        return productDto;
    }

}
