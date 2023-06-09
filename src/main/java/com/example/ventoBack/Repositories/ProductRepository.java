package com.example.ventoBack.Repositories;

import com.example.ventoBack.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByEventNameContainingIgnoreCase(String eventName, Pageable pageable);
    List<Product> findByCategory(String category);
}
