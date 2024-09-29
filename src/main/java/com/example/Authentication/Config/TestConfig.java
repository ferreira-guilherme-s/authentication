package com.example.Authentication.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.entities.Entities.UserEntity;
import com.example.repositories.UserRepository;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        UserEntity firstUser = new UserEntity(null, "Guilherme Ferreira", "guilhermefs159@gmail.com", "988888888", "123456"); 
        UserEntity secondUser = new UserEntity(null, "Yasmin", "yasmin@gmail.com", "977777777", "123456"); 

        userRepository.saveAll(Arrays.asList(firstUser, secondUser));
    }
}
