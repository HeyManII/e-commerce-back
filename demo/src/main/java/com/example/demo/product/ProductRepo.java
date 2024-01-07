package com.example.demo.product; 

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product, String>{
    
    @Query("{'category': ?0}")
    List<Product> findByCategory(String category);
}
