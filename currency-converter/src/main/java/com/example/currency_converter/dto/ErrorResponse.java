package com.example.currency_converter.dto;

public class ErrorResponse {
    
    private String status;
    private String errorMessage;
    private Integer errorCode;

    public ErrorResponse(String status, String errorMessage, Integer errorCode) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }



    public void setStatus(String status) {this.status = status; }
    
    public String getStatus() { return this.status; }

    public String getErrorMessage() { return this.errorMessage; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public void setErrorCode(Integer errorCode) { this.errorCode = errorCode; }

    public Integer getErrorCode() { return this.errorCode; }
    
}
