package com.example.currency_converter.exceptions;

public class MissingValue extends Exception {

    private Integer errorCode;

    public MissingValue(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() { return errorCode; }
    
}
