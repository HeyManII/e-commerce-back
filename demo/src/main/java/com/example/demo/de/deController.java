package com.example.demo.de;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/demo")
public class deController {
    
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World!");
    }
    
}
