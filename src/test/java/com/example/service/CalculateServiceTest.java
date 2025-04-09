package com.example.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateServiceTest {

    private final CalculateService calculateService = new CalculateService();

    @Test
    public void testCalculateWithVacationData() {
        double averageSalary = 30000;
        int vacationDays = 5;

        double result = calculateService.calculate(averageSalary, vacationDays);

        assertEquals(5119.45, result, 0.01);
    }

    @Test
    public void testCalculateWithDates() {
        double averageSalary = 30000;
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 3);

        double result = calculateService.calculate(averageSalary, startDate, endDate);

        assertEquals(1023.89, result, 0.01);
    }

    @Test
    public void testCalculateWithWeekends() {
        double averageSalary = 30000;
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 2);

        double result = calculateService.calculate(averageSalary, startDate, endDate);

        assertEquals(0.0, result, 0.01);
    }

    @Test
    public void testCalculateWithHolidays() {
        double averageSalary = 30000;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 8);

        double result = calculateService.calculate(averageSalary, startDate, endDate);

        assertEquals(1023.89, result, 0.01);
    }
}