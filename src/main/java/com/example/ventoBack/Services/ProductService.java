package com.example.ventoBack.Services;

import com.example.ventoBack.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product addProduct(Product product);

    Product editProduct(Product product);

    void deleteProduct(Long id);

    Page<Product> getRequestFilters(int page,int limit,String eventName,Sort.Direction sortType);

    List<Product> fetchProductByCategory(String category);

}
