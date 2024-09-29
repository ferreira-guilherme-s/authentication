package com.example.resources.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authentication.Validations.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "")
@Validated()
public class Authentication {
    @PostMapping(value = "/create_user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createUser(@Valid @RequestBody User body) {
        return ResponseEntity.ok("Usuário recebido com sucesso " + body.getName());
    }
}
