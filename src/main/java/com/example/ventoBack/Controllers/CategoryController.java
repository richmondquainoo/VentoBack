package com.example.springApp.Controllers;

import com.example.springApp.Entities.Category;
import com.example.springApp.Handlers.ResponseHandler;
import com.example.springApp.Services.CategoryService;
import com.example.springApp.Services.ServicesImp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private final CategoryServiceImp categoryServiceImp;

    public CategoryController(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }


    @GetMapping("/fetchAllCategories")
    public ResponseEntity<Object> fetchAllCategories(){
        try{
            categoryServiceImp.getAllCategory();
            return ResponseHandler.handleResponse("Successfully fetched categories",HttpStatus.OK,categoryServiceImp);

        }catch(Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PostMapping("/createCategory")
    public ResponseEntity<Object> createCategory(@RequestBody Category category){
        try{
            categoryServiceImp.saveCategory(category);
            return ResponseHandler.handleResponse("Successfully created category",HttpStatus.OK,category);

        }catch(Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
