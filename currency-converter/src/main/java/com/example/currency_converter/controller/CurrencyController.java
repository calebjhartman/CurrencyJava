package com.example.currency_converter.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.currency_converter.dto.ConversionResponse;
import com.example.currency_converter.dto.ErrorResponse;
import com.example.currency_converter.exceptions.CurrencyCodeNotFound;
import com.example.currency_converter.service.CurrencyService;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/curr/")
public class CurrencyController {

    private final CurrencyService currencyService;


    // Prepare for the injection of the depdendecy
    public CurrencyController(CurrencyService currencyService) { this.currencyService = currencyService; }

    // Public convert -- no sign-in needed.
    @GetMapping("/convert")
    public ResponseEntity<?> convertCurrencies(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) { 
        try {
            // Get the conversion response
            ConversionResponse conversion = currencyService.convert(from, to, amount);
            System.out.println("CONVERSION: " + from + " --> " + to + " @ " + conversion.getRate() + " -- " + conversion.getConvertedAmount());
            // Wrap it in a response "OK" entity
            return ResponseEntity.status(200).body(conversion);
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
