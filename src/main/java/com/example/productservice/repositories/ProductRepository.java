package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product save(Product product);

    Optional<Product> findById(long id);

    @Query(value = "select * from product", nativeQuery = true)
    Page<Product> getAllProducts(Pageable pageable);

    void deleteById(Long id);
}
