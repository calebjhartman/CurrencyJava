package com.example.currency_converter.exceptions;

public class CurrencyCodeNotFound extends Exception {

    private Integer errorCode;

    public CurrencyCodeNotFound(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() { return errorCode; }
    
}
