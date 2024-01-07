package com.example.demo.auth;

import com.example.demo.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String _id;
    private String username;
    private String email;
    private Role role;
    private String token;
}