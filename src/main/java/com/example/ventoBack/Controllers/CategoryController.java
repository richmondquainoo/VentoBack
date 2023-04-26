package com.example.ventoBack.Controllers;

import com.example.ventoBack.Entities.Category;
import com.example.ventoBack.Entities.Product;
import com.example.ventoBack.Handlers.ResponseHandler;
import com.example.ventoBack.Services.ServicesImp.CategoryServiceImp;
import com.example.ventoBack.Services.ServicesImp.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/category")
public class CategoryController {


    private final CategoryServiceImp categoryServiceImp;
    private final ProductServiceImp productServiceImp;



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


    @PutMapping("/editCategory")
    public ResponseEntity<Object> editProduct(@RequestBody @Valid Category category){
        try {
            Category editedCategory = categoryServiceImp.editCategory(category);
            if(editedCategory!=null){
                return ResponseHandler.handleResponse("Successfully edited Category", HttpStatus.OK,editedCategory);
            }else{
                return ResponseHandler.handleResponse("Category Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
        try {
            categoryServiceImp.deleteCategory(id);
            return ResponseHandler.handleResponse("Successfully deleted category", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


}
