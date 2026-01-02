package com.example.currency_converter.service;

import java.math.BigDecimal;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.example.currency_converter.dto.ExternalRatesResponse;
import com.example.currency_converter.exceptions.CurrencyCodeNotFound;



@Service
public class CurrencyService {


    
    @Value("${currency.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();


    public BigDecimal getRate(String from, String to) throws CurrencyCodeNotFound{
        String url = apiUrl + from;

        try {
            ExternalRatesResponse response = restTemplate.getForObject(url, ExternalRatesResponse.class);
            // If the API is down or otherwise does not return a response.
            if (response == null) { throw new RuntimeException("The API appears to be down."); }

            if (response.getResult().equals("success")) {

                BigDecimal rate = response.getConversion_Rates().get(to);
                // Throw excepption if the "to" currency code was not found in the map
                if (rate == null) { throw new CurrencyCodeNotFound("The 'to' currency code of " + to + " does not exist.", 400); }

                return rate;
            }
            else { throw new CurrencyCodeNotFound("The 'from' currency code of " + from + " does not exist.", 400); }
        }
        // We are catching all other exceptions in the second catch, so we need to re-throw them here as to not erase the error code
        catch (CurrencyCodeNotFound e) { throw new CurrencyCodeNotFound("The 'from' currency code of " + from + " does not exist.", 400); }
        catch (Exception e) { throw new RuntimeException("An error occured:" + e.getMessage(), e); }   
    }

    
}
