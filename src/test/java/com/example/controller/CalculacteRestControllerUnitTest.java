package com.example.controller;

import com.example.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

public class CalculacteRestControllerUnitTest {

    private final CalculateService calculateService = Mockito.mock(CalculateService.class);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new CalculacteRestController(calculateService))
            .build();

    @Test
    public void testCalculate() throws Exception {
        when(calculateService.calculate(30000.0, 
                java.time.LocalDate.parse("2024-06-01"), 
                java.time.LocalDate.parse("2024-06-03")))
                .thenReturn(1023.89);
                
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", "30000")
                        .param("startDate", "2024-06-01")
                        .param("endDate", "2024-06-03"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1023.89"));
    }
}