package com.example.demo.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
 
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam("category") String category) {
        if (productService.getProductsByCategory(category).isEmpty()) {
            throw new IllegalStateException("No products with category " + category + " exist");
        }
        System.out.println(category);
        return ResponseEntity.ok().body(productService.getProductsByCategory(category));
    }   

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id){
        Product target = productService.getProductById(id);
        if (target == null) {
            throw new IllegalStateException("Product with id " + id + " does not exist");
        }
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @NotNull @RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(201).body(product);
    }
    
}