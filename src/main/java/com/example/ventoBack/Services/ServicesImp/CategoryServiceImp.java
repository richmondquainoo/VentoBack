package com.example.ventoBack.Services.ServicesImp;

import com.example.ventoBack.Entities.Category;
import com.example.ventoBack.Entities.Product;
import com.example.ventoBack.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Primary
public class CategoryServiceImp {


//    private final Product product;
    private final CategoryRepository categoryRepository;


    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }


    public Category editCategory(Category category) {
        boolean exist = categoryRepository.existsById(category.getId());
        if(exist){
            return categoryRepository.save(category);
        }
        return null;
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }


}
