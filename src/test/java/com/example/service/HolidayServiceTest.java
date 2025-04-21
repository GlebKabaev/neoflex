package com.example.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HolidayServiceTest {

    private final HolidayService holidayService = new HolidayService();

    @Test
    void testWorkingDaysWithoutWeekendsOrHolidays() {
        LocalDate start = LocalDate.of(2025, 4, 14); // понедельник
        LocalDate end = LocalDate.of(2025, 4, 18);   // пятница
        int result = holidayService.countWorkingDays(start, end);
        assertEquals(5, result);
    }

    @Test
    void testWorkingDaysWithWeekend() {
        LocalDate start = LocalDate.of(2025, 4, 12); // суббота
        LocalDate end = LocalDate.of(2025, 4, 14);   // понедельник
        int result = holidayService.countWorkingDays(start, end);
        assertEquals(1, result); 
    }

    @Test
    void testWorkingDaysOnNewYearHoliday() {
        LocalDate start = LocalDate.of(2025, 1, 1);
        LocalDate end = LocalDate.of(2025, 1, 7);
        int result = holidayService.countWorkingDays(start, end);
        assertEquals(0, result); //выходные
    }

    @Test
    void testWorkingDaysWithHolidayInTheMiddle() {
        LocalDate start = LocalDate.of(2025, 5, 8); // четверг
        LocalDate end = LocalDate.of(2025, 5, 10);  // суббота
        int result = holidayService.countWorkingDays(start, end);
        assertEquals(1, result); // 8 рабочий, 9 праздник, 10 суббота
    }

    @Test
    void testSingleWorkingDay() {
        LocalDate start = LocalDate.of(2025, 4, 15);
        LocalDate end = LocalDate.of(2025, 4, 15);
        int result = holidayService.countWorkingDays(start, end);
        assertEquals(1, result);
    }

    @Test
    void testSingleHoliday() {
        LocalDate date = LocalDate.of(2025, 1, 1); // Новый год
        int result = holidayService.countWorkingDays(date, date);
        assertEquals(0, result);
    }
    @Test
    void enotherTest(){
        LocalDate start = LocalDate.of(2007, 2, 1);
        LocalDate end = LocalDate.of(2007, 3, 1);
        int result = holidayService.countWorkingDays(start, end);
        assertEquals(20, result);
    }
}
