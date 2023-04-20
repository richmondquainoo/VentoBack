package com.example.springApp.Controllers;


import com.example.springApp.Entities.Product;
import com.example.springApp.Services.ProductService;
import com.example.springApp.Handlers.ResponseHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
