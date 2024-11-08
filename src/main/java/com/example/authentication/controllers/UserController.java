package com.example.authentication.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.dtos.UserCreateDTO;
import com.example.authentication.dtos.UserDTO;
import com.example.authentication.dtos.UserLoginDTO;
import com.example.authentication.entities.UserEntity;
import com.example.authentication.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<UserDTO> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/users/create")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserCreateDTO user) {
        UserEntity createdUser = userService.createUser(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("users/login")
    public ResponseEntity<Optional<UserEntity>> login(@Valid @RequestBody UserLoginDTO user) {
        Optional<UserEntity> login = userService.login(user);

        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
