package com.example.demo.product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(String id){
        return productRepo.findById(id).orElse(null);
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }

    public List<Product> getProductsByCategory(String category){
        return productRepo.findByCategory(category);
    }

}