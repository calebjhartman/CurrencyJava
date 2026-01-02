package com.example.currency_converter.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ExternalRatesResponse {

    private String result;
    private String baseCode;
    private Map<String, BigDecimal> conversion_rates;

    public ExternalRatesResponse(String result, String baseCode, Map<String, BigDecimal> conversion_rates) {
        this.result = result;
        this.baseCode = baseCode;
        this.conversion_rates = conversion_rates;

    }

    public void setResult(String result) { this.result = result; }
    
    public String getResult() { return this.result; }

    public void setBaseCode(String baseCode) { this.baseCode = baseCode; }

    public String getBaseCode() { return this.baseCode; }

    public void setConversion_Rates(Map<String, BigDecimal> conversion_rates) { this.conversion_rates = conversion_rates; }

    public Map<String, BigDecimal> getConversion_Rates() { return conversion_rates; }

    
}
