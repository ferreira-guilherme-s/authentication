package com.example.authentication.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.authentication.dtos.UserCreateDTO;
import com.example.authentication.dtos.UserDTO;
import com.example.authentication.dtos.UserMapper;
import com.example.authentication.entities.UserEntity;
import com.example.authentication.repositories.UserRepository;
import com.example.authentication.utils.HashHelper;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream()
            .map(UserMapper::toDTO)
            .collect(Collectors.toList());
    }

    public UserEntity createUser(UserCreateDTO user) {
        String passwordHash = "";
        try {
            passwordHash = HashHelper.generateHash(user.getPassword());   
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserEntity newUser = new UserEntity();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordHash);

        return userRepository.save(newUser);
    }

}
