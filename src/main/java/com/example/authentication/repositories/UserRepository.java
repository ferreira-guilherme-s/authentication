package com.example.authentication.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authentication.entities.UserEntity;

public interface UserRepository extends  JpaRepository<UserEntity, UUID> {
    List<UserEntity> findByFullName(String fullName);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(UUID id);
}
