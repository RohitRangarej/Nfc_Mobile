package com.nfc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nfc.entities.Users;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByEmail(String email);
}
