package com.example.ventoBack.Services.ServicesImp;

import com.example.ventoBack.Entities.Product;
import com.example.ventoBack.Repositories.ProductRepository;
import com.example.ventoBack.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final  ProductRepository productRepository;


    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product) {
        boolean exist = productRepository.existsById(product.getId());
        if(exist){
          return productRepository.save(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getRequestFilters(int page,int limit,String eventName, Sort.Direction sortType) {
        Page<Product> eventPage = null;
        if(eventName==null && sortType==null){
            eventPage = getProductsList(page,limit);
        }
        if(eventName!=null && sortType==null ){
            eventPage = findProductsByName(page,limit,eventName);
        }
        if(eventName==null && sortType != null ){
            eventPage = getProductsOrderByPrice(page,limit,sortType);
        }
        if(eventName!=null && sortType!=null){
            eventPage = findProductsByNameAndOrderByPrice(page,limit,eventName,sortType);
        }
        return  eventPage;
    }


    public List<Product> fetchProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    

    private Page<Product> getProductsList(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return productRepository.findAll(pageable);
    }

    private Page<Product> findProductsByName(int page,int limit,String eventName) {
        Pageable pageable = PageRequest.of(page, limit);
        return productRepository.findByEventNameContainingIgnoreCase(eventName, pageable);
    }


    private Page<Product> getProductsOrderByPrice(int page, int limit,Sort.Direction sortType) {
        Sort sort = Sort.by(sortType, "rate");
        Pageable pageable = PageRequest.of(page, limit,sort);
        return productRepository.findAll(pageable);
    }

    private Page<Product> findProductsByNameAndOrderByPrice(int page, int limit,
                                                           String eventName,
                                                           Sort.Direction sortType) {
        Sort sort = Sort.by(sortType, "rate");
        Pageable pageable = PageRequest.of(page, limit,sort);
        return productRepository.findByEventNameContainingIgnoreCase(eventName,pageable);
    }

//
//    private List<Product> fetchProductsByCategory(String category){
//        return productRepository.findByCategory(category);
//    }
}
