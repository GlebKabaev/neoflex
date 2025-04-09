package com.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculacteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculate() throws Exception {

        mockMvc.perform(get("/calculate")
                .param("averageSalary", "30000")
                .param("startDate", "2024-06-01")
                .param("endDate", "2024-06-03"))
                .andExpect(status().isOk())
                .andExpect(content().string("1023.89"));
    }

    @Test
    public void testCalculateWithDifferentDates() throws Exception {
        mockMvc.perform(get("/calculate")
                .param("averageSalary", "50000")
                .param("startDate", "2024-07-01")
                .param("endDate", "2024-07-05"))
                .andExpect(status().isOk())
                .andExpect(content().string("8532.42"));
    }

    @Test
    public void testCalculateWithWeekends() throws Exception {
        mockMvc.perform(get("/calculate")
                .param("averageSalary", "40000")
                .param("startDate", "2024-06-08")
                .param("endDate", "2024-06-09"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }

    @Test
    public void testCalculateWithHolidays() throws Exception {
        mockMvc.perform(get("/calculate")
                .param("averageSalary", "40000")
                .param("startDate", "2024-01-01")
                .param("endDate", "2024-01-03"))
                .andExpect(status().isOk())
                .andExpect(content().string("0.0"));
    }
}