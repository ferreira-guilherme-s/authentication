package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
