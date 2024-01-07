package com.example.demo.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Data
@Builder
@Document(collection = "users")
public class User implements UserDetails{

    @Id
    private String id;
    @NotBlank(message = "Username is required")
    private String username;
    @Indexed(unique = true)
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getId() {
        return id;
    }

    public String getName(){
        return username;
    }

    public String getUsername() {
        return email;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    public Role getRole(){
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}