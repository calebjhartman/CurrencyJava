package com.example.currency_converter.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.currency_converter.dto.ConversionResponse;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class CurrencyController {

@GetMapping("/convert")
public ConversionResponse convertCurrencies(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
    BigDecimal rate = new BigDecimal("1.05");
    BigDecimal convertedAmount = amount.multiply(rate);

    return new ConversionResponse(from, to, amount, rate, convertedAmount); 
}


    
}
