package com.example.currency_converter.dto;

import java.math.BigDecimal;
// This is really just an object that Spring automatically uses to create JSON objects returned to the endpoint. 
// It automatically makes use of the getters and setters.
public class ConversionResponse {

    private String status;
    private String from;
    private String to;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal convertedAmount;

    public ConversionResponse() {
    }


    public ConversionResponse(String status, String from, String to, BigDecimal amount, BigDecimal rate, BigDecimal convertedAmount) {
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.convertedAmount = convertedAmount;
    }

    public void setStatus(String status) {this.status = status; }
    
    public String getStatus() { return this.status; }
    
    public void setFrom(String from) {this.from = from; }
    
    public String getFrom() { return this.from; }

    public void setTo(String to) { this.to = to; }

    public String getTo() { return this.to; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getAmount() { return this.amount; }

    public void setRate(BigDecimal rate) { this.rate = rate; }

    public BigDecimal getRate() { return this.rate; }

    public void setConvertedAmount(BigDecimal convertedAmount) { this.convertedAmount = convertedAmount; }

    public BigDecimal getConvertedAmount() { return this.convertedAmount; }

}
