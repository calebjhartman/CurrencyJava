package com.example.currency_converter;

import com.example.currency_converter.dto.ConversionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publicConvert_ShouldReturn200AndCorrectBody() throws Exception {
        mockMvc.perform(get("/api/curr/convert")
                .param("from", "USD")
                .param("to", "EUR")
                .param("amount", "100")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.convertedAmount").exists())
            .andExpect(jsonPath("$.from").value("USD"))
            .andExpect(jsonPath("$.to").value("EUR"));
    }

    @Test
    void publicConvert_ShouldReturn400ErrorBody() throws Exception {
        mockMvc.perform(get("/api/curr/convert")
            .param("from", "12345")
            .param("to", "EUR")
            .param("amount", "100")
            .accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value("failed"));


    }
}

