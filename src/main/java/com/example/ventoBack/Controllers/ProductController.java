package com.example.ventoBack.Controllers;


import com.example.ventoBack.Entities.Product;
import com.example.ventoBack.Services.ProductService;
import com.example.ventoBack.Handlers.ResponseHandler;
import com.example.ventoBack.Services.ServicesImp.ProductServiceImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/events")
public class ProductController {


    private final ProductService productService;
    private final ProductServiceImp productServiceImp;



    public ProductController(ProductService productService, ProductServiceImp productServiceImp) {
        this.productService = productService;
        this.productServiceImp = productServiceImp;
    }

    @GetMapping
    public ResponseEntity<Object> getProducts(@RequestParam(required = false,defaultValue = "0") int page,
                                              @RequestParam(required = false,defaultValue = "5") int limit,
                                              @RequestParam(required = false)  String eventName,
                                              @RequestParam(required = false) Sort.Direction sortType){
        try {
            Page<Product> productPage = productService.getRequestFilters(page,limit,eventName,sortType);
          return ResponseHandler.handleResponse("Successfully get products",HttpStatus.OK,productPage);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR",HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PostMapping("/addEvent")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product){
        try {
            Product addedProduct = productService.addProduct(product);
            return ResponseHandler.handleResponse("Successfully added event", HttpStatus.OK,addedProduct);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @GetMapping("/fetchProductsByCategory/{category}")
    public ResponseEntity<Object> fetchProductsByCategory(@PathVariable String category){
        try{
            List<Product> fetchedProducts = productServiceImp.fetchProductByCategory(category);
            return ResponseHandler.handleResponse("Successfully fetched products by category",HttpStatus.OK,fetchedProducts);

        }catch(Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }


    @PutMapping("/editEvent")
    public ResponseEntity<Object> editProduct(@RequestBody @Valid Product product){
        try {
            Product editedProduct = productService.editProduct(product);
            if(editedProduct!=null){
                return ResponseHandler.handleResponse("Successfully edited Event", HttpStatus.OK,editedProduct);
            }else{
                return ResponseHandler.handleResponse("Event Id Not exist", HttpStatus.BAD_REQUEST,null);
            }
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProduct(id);
            return ResponseHandler.handleResponse("Successfully deleted event", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.handleResponse("ERROR", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}
