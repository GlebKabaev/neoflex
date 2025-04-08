package com.example.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.model.VacationData;

@Service
public class CalculateService {
    private Set<LocalDate> getHolidays(int year) {
        Set<LocalDate> holidays = new HashSet<>();
        holidays.add(LocalDate.of(year, Month.JANUARY, 1)); // Новый год
        holidays.add(LocalDate.of(year, Month.JANUARY, 2)); // Новый год
        holidays.add(LocalDate.of(year, Month.JANUARY, 3)); // Новый год
        holidays.add(LocalDate.of(year, Month.JANUARY, 4)); // Новый год
        holidays.add(LocalDate.of(year, Month.JANUARY, 5)); // Новый год
        holidays.add(LocalDate.of(year, Month.JANUARY, 6)); // Новый год
        holidays.add(LocalDate.of(year, Month.JANUARY, 7)); // Рождество Христово
        holidays.add(LocalDate.of(year, Month.FEBRUARY, 23)); // День защитника Отечества
        holidays.add(LocalDate.of(year, Month.MARCH, 8)); // Международный женский день
        holidays.add(LocalDate.of(year, Month.MAY, 1)); // Праздник Весны и Труда
        holidays.add(LocalDate.of(year, Month.MAY, 9)); // День Победы
        holidays.add(LocalDate.of(year, Month.JUNE, 12)); // День России
        holidays.add(LocalDate.of(year, Month.NOVEMBER, 4)); // День народного единства
        return holidays;
    }

    public double calculate(VacationData vacationData) {
        return (vacationData.getAverageSalary() / 29.3) * vacationData.getVacationDays();
    }

    public double calculate(double averageSalary, int vacationDays) {
        return (averageSalary / 29.3) * vacationDays;
    }

    public double calculate(double averageSalary, LocalDate startDate, LocalDate endDate) {
        int workingDays = countWorkingDays(startDate, endDate);
        double rawResult = (averageSalary / 29.3) * workingDays;

        
        BigDecimal roundedResult = new BigDecimal(rawResult).setScale(2, RoundingMode.HALF_UP);
        return roundedResult.doubleValue();
    }

    private int countWorkingDays(LocalDate startDate, LocalDate endDate) {
        int workingDays = 0;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (isWorkingDay(date)) {
                workingDays++;
            }
        }

        return workingDays;
    }

    private boolean isWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return !(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
                || getHolidays(date.getYear()).contains(date));
    }
}
