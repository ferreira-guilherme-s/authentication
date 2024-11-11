package com.example.authentication.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authentication.dtos.UserCreateDTO;
import com.example.authentication.dtos.UserDTO;
import com.example.authentication.dtos.UserLoginDTO;
import com.example.authentication.dtos.UserMapper;
import com.example.authentication.dtos.UserUpdateDTO;
import com.example.authentication.entities.UserEntity;
import com.example.authentication.repositories.UserRepository;
import com.example.authentication.utils.HashHelper;

@Service
public class UserService {

    private final UserRepository userRepository;
        private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public Optional<UserEntity> login(UserLoginDTO body) {
        Optional<UserEntity> optUser = userRepository.findByEmail(body.getEmail());

        if (optUser.isPresent()) {
            UserEntity user = optUser.get();
            String hashPassword = "";
            try {
                hashPassword =HashHelper.generateHash(body.getPassword());  
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if (hashPassword.equals(user.getPassword())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<UserEntity> updateUser(UUID id, UserUpdateDTO body) throws Exception {
        Optional<UserEntity> findUser = userRepository.findById(id);

        if(findUser.isPresent()) {
            UserEntity user = findUser.get();
            try {
                user.setFullName(Objects.requireNonNullElse(body.getFullName(), user.getFullName()));
                user.setEmail(Objects.requireNonNullElse(body.getEmail(), user.getEmail()));

                userRepository.save(user);

                return Optional.of(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }

}
