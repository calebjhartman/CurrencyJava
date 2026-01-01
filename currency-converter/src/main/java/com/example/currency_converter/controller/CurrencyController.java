package com.example.currency_converter.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.currency_converter.dto.ConversionResponse;
import com.example.currency_converter.service.CurrencyService;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;

    // Prepare for the injection of the depdendecy
    public CurrencyController(CurrencyService currencyService) { this.currencyService = currencyService; }

    @GetMapping("/convert")
    public ConversionResponse convertCurrencies(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
        BigDecimal rate = currencyService.getRate(from, to);
        BigDecimal convertedAmount = amount.multiply(rate);

        return new ConversionResponse(from, to, amount, rate, convertedAmount); 
    }


    
}
