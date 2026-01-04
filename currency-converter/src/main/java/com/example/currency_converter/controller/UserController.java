package com.example.currency_converter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.currency_converter.repository.UserRepository;
import com.example.currency_converter.service.CurrencyService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.currency_converter.dto.ConversionResponse;
import com.example.currency_converter.dto.ErrorResponse;
import com.example.currency_converter.entity.User;
import com.example.currency_converter.exceptions.CurrencyCodeNotFound;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final CurrencyService currencyService;

    public UserController(UserRepository userRepository, CurrencyService currencyService) { this.userRepository = userRepository; this.currencyService = currencyService;}

    // @RequestBody maps what is sent via the post body to the JSON user with the user entity.
    @PostMapping
    public User createUser(@RequestBody User userEntity) {
        return userRepository.save(userEntity);
    }

    // Get a user given their ID
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

    @GetMapping("/convert")
    public ResponseEntity<?> convertUserSpecificAmount(@RequestParam long id, @RequestParam String to, @RequestParam BigDecimal amount, @RequestParam(name="from") Optional<String> optionalFrom) {
        String from = null;

        // Get the user's favored currency
        Optional<User> optionalUser = userRepository.findById(id);
        try {
            if (optionalUser.isPresent()) {
                User user = optionalUser.get(); // Unwrap

                // If a from is provided, unwrap it.
                if (optionalFrom.isPresent()) { from = optionalFrom.get(); }
                // Otherwise... grab the user's fav. code.
                else { from = user.getFavCurrencyCode(); }

                // Get the conversion response
                ConversionResponse conversion = currencyService.convert(from, to, amount); 
                System.out.println(amount + " CONVERSION: " + from + " --> " + to + " @ " + conversion.getRate() + " -- " + conversion.getConvertedAmount());

                // Wrap it in a response "OK" entity
                return ResponseEntity.status(200).body(conversion);
            }
            else {
                ErrorResponse error = new ErrorResponse("USER_NOT_FOUND", "User with ID " + id + " not found", 404 );
                return ResponseEntity.status(404).body(error);
            }
        }
        // When a currency code is not found.
        catch (CurrencyCodeNotFound e) {
            System.out.println("FAILED CONVERSION: " + from + " --> " + to);
            ErrorResponse error = new ErrorResponse("failed", e.getMessage(), e.getErrorCode());
            return ResponseEntity.status(e.getErrorCode()).body(error);
        }
        // All other exceptions
        catch (Exception e) {
            System.out.println("FAILED CONVERSION: " + from + " --> " + to);
            ErrorResponse error = new ErrorResponse("failed", e.getMessage(), 502);

            return ResponseEntity.status(502).body(error);

        }
    }
    
}
