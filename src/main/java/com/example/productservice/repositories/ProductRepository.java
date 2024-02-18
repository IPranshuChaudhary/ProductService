package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product save(Product product);

    Optional<Product> findById(long id);

    @Query(value = "select * from product", nativeQuery = true)
    Optional<Product []> getAllProducts();

    void deleteById(Long id);
}
