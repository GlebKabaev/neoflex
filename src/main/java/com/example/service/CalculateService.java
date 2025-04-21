package com.example.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    private HolidayService holidayService;
    public CalculateService(HolidayService holidayService){
        this.holidayService=holidayService;
    }

    public double calculate(double averageSalary, int vacationDays) {
        if (averageSalary < 0 || vacationDays < 0) {
            throw new IllegalArgumentException("Некорректные входные данные: проверьте зарплату и количество дней");
        }
        double rawResult =(averageSalary / 29.3) * vacationDays;
        BigDecimal roundedResult = new BigDecimal(rawResult).setScale(2, RoundingMode.HALF_UP);
        return roundedResult.doubleValue();
    }
    
    public double calculate(double salary, String startDate, String endDate, Integer days) {
        if (startDate != null && endDate != null) {
            return calculate(salary, LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if (days != null) {
            return calculate(salary, days);
        }
        throw new IllegalArgumentException("Некорректные входные данные: проверьте зарплату и количество дней");
    }
    

    public double calculate(double averageSalary, LocalDate startDate, LocalDate endDate) {
        if (!isValid(averageSalary, startDate, endDate)) {
            throw new IllegalArgumentException("Некорректные входные данные: проверьте зарплату и даты");
        }
        int workingDays = holidayService.countWorkingDays(startDate, endDate);
        double rawResult = calculate(averageSalary, workingDays);
        BigDecimal roundedResult = new BigDecimal(rawResult).setScale(2, RoundingMode.HALF_UP);
        return roundedResult.doubleValue();
    }

    private boolean isValid(double averageSalary, LocalDate startDate, LocalDate endDate) {
        return !(startDate.isAfter(endDate) || averageSalary < 0);
    }
}
