package com.example.ventoBack.Services;

import com.example.ventoBack.Entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    Category deleteCategory();

    Category editCategory();
}
