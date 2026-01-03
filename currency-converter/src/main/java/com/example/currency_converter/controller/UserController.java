package com.example.currency_converter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.currency_converter.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.currency_converter.dto.ErrorResponse;
import com.example.currency_converter.entity.User;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) { this.userRepository = userRepository; }

    // @RequestBody maps what is sent via the post body to the JSON user with the user entity
    @PostMapping
    public User createUser(@RequestBody User userEntity) {
        return userRepository.save(userEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        // If a user was found... return a user DTO. 
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        // Otherwise... send an error. 
        } else {
            ErrorResponse error = new ErrorResponse(
                "USER_NOT_FOUND",
                "User with ID " + id + " not found",
                404
            );
        return ResponseEntity.status(404).body(error);
    }

    }
    
}
