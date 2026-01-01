package com.example.currency_converter.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    

    public BigDecimal getRate(String to, String from) {
        return new BigDecimal("1.25");
    }
}
