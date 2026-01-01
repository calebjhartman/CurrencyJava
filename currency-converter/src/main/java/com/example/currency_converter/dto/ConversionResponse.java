package com.example.currency_converter.dto;

import java.math.BigDecimal;

public class ConversionResponse {

    private String from;
    private String to;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal convertedAmount;

    public ConversionResponse() {
    }


    public ConversionResponse(String from, String to, BigDecimal amount, BigDecimal rate, BigDecimal convertedAmount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.convertedAmount = convertedAmount;
    }
    
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
