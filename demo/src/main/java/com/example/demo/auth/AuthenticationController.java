package com.example.demo.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request){
                return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("logout")
    public ResponseEntity<AuthenticationResponse> logout (@RequestBody AuthenticationRequest request){
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();    
    }

}
