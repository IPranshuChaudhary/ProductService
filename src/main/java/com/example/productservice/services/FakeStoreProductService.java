package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.FakeStoreProduct;
import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Primary
@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
//    private RedisTemplateConfig redisTemplateConfig;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
//        this.redisTemplateConfig = redisTemplateConfig;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
//        RedisTemplate<String, Object> restTemplate1 = redisTemplateConfig.redisTemplate();

        Product product = new Product();

//        product = (Product) restTemplate1.opsForHash().get("PRODUCTS","Product_"+id);

//        if (product != null){
//            return product;
//        }
        FakeStoreProduct fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProduct.class);

        product = getProductFromFakeStoreProduct(fakeStoreProduct);

        //storing product in Redis Map
//        restTemplate1.opsForHash().put("PRODUCTS","Product_"+id, product);

        return product;
    }

    @Override
    public List<Product> getAllProducts(int pageNumber, int pageSize, String sortBy) throws ProductNotFoundException {
        FakeStoreProduct[] fakeStoreProducts = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProduct[].class);
        List<Product> productsList = new ArrayList<>();

        for (FakeStoreProduct fakeStoreProduct: fakeStoreProducts){
            productsList.add(getProductFromFakeStoreProduct(fakeStoreProduct));
        }
        return productsList;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public Product updateProduct(Product product) throws ProductNotFoundException {
        return null;
    }

    public Product getProductFromFakeStoreProduct(FakeStoreProduct fakeStoreProduct){
        Product product = new Product();
        product.setId(fakeStoreProduct.getId());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setDescription(fakeStoreProduct.getDescription());
//        product.setPrice(fakeStoreProduct.getPrice());
        return product;

    }
}
