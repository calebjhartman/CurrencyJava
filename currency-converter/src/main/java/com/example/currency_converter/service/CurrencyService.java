package com.example.currency_converter.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.currency_converter.dto.ConversionResponse;
import com.example.currency_converter.dto.ExternalRatesResponse;
import com.example.currency_converter.exceptions.CurrencyCodeNotFound;

@Service
public class CurrencyService {

    @Value("${currency.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // Return the bigdecimal rate or throw an exception if incorrect from/to codes are provided
    public BigDecimal getRate(String from, String to) throws CurrencyCodeNotFound{

        String url = apiUrl + from;
        ExternalRatesResponse response;

        // If an error is returned (caused by an incorrect 'from' code), throw an error
        try { response = restTemplate.getForObject(url, ExternalRatesResponse.class); }
        catch (Exception e) { throw new CurrencyCodeNotFound("The 'from' currency code of " + from + " does not exist.", 400);  }

        // If the API is down or otherwise does not return a response.
        if (response == null) { throw new RuntimeException("The API appears to be down."); }

        // We had a succesful conversion
        System.out.println(response.getResult());
        BigDecimal rate = response.getConversion_Rates().get(to);

        // Throw excepption if the "to" currency code was not found in the map
        if (rate == null) { throw new CurrencyCodeNotFound("The 'to' currency code of " + to + " does not exist.", 400); }

        // Return the rate we have found with the from and to currency codes
        return rate;
    }

    // Returns a ConversionResponse
    public ConversionResponse convert(String from, String to, BigDecimal amount) throws CurrencyCodeNotFound {
         try {
            // Request the rate for the given base currency to a given code
            BigDecimal rate = this.getRate(from, to);

            // Multiply the amount given by the rate
            BigDecimal convertedAmount = amount.multiply(rate);
            
            return new ConversionResponse("success", from, to, amount, rate, convertedAmount);
        }
        // Bubble the exceptions up
        catch (CurrencyCodeNotFound e) { throw e; }
        catch (Exception e) { throw e; }

    }

    
}
