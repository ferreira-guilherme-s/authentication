package com.example.resources.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entities.Entities.UserEntity;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @GetMapping
    public ResponseEntity<UserEntity> findAll() {
        UserEntity u = new UserEntity(1L, "Guilherme", "guilhermefs159@gmail.com", "999999", "12345");
        return ResponseEntity.ok().body(u);
    }
}
