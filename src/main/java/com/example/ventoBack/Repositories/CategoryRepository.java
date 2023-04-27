package com.example.ventoBack.Repositories;

import com.example.ventoBack.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    List<Category> findAll();

    Category deleteCategoryById(Long id);
}
