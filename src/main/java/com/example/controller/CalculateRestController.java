package com.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.CalculateService;

@RestController
public class CalculateRestController {
    private CalculateService calculacteService;

    public CalculateRestController(CalculateService calculacteService) {
        this.calculacteService = calculacteService;
    }

    @GetMapping("/calculate")
    public double calculate(@RequestParam double averageSalary,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Integer vacationDays) {
        return calculacteService.calculate(averageSalary, startDate, endDate, vacationDays);
    }

    @ExceptionHandler({ IllegalArgumentException.class, DateTimeParseException.class })
    public ResponseEntity<String> handleBadRequest(Exception ex) {
        return new ResponseEntity<>("Ошибка: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
