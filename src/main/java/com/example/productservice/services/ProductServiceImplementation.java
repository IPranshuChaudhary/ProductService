package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class ProductServiceImplementation implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

//    private RedisTemplateConfig redisTemplateConfig;

    ProductServiceImplementation(ProductRepository productRepository,
                                 CategoryRepository categoryRepository
                                 ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
//        this.redisTemplateConfig = redisTemplateConfig;
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

//        RedisTemplate<String, Object> redisTemplate = redisTemplateConfig.redisTemplate();
        Product product = new Product();
//        product = (Product) redisTemplate.opsForHash().get("PRODUCTS","Product_"+id);

//        if (product != null) {
//            return product;
//        }

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new ProductNotFoundException("Product with id: "+id+" doesn't exist");
        }
        product = productOptional.get();

//        redisTemplate.opsForHash().put("PRODUCTS","Product_"+id,product);
        return product;
    }

    @Override
    public List<Product> getAllProducts(int pageNumber, int pageSize, String sortBy) throws ProductNotFoundException {
        Pageable firstPageWithTwoElements = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Product> optionalProducts = productRepository.getAllProducts(firstPageWithTwoElements);
        System.out.println(optionalProducts);

        if (optionalProducts.isEmpty()){
            throw new ProductNotFoundException("No Products Found");
        }

        List<Product> productList = new ArrayList<>();
        for (Product product: optionalProducts){
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
