package com.example.currency_converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.currency_converter.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // optional custom method:
}
