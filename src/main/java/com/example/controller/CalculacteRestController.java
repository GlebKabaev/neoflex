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
public class CalculacteRestController {
    CalculateService calculacteService;

    CalculacteRestController(CalculateService calculacteService) {
        this.calculacteService = calculacteService;
    }

    @GetMapping("/calculate")
    public double calculacte(@RequestParam double averageSalary,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return calculacteService.calculate(averageSalary, start, end);
    }

    @ExceptionHandler({ IllegalArgumentException.class, DateTimeParseException.class })
    public ResponseEntity<String> handleBadRequest(Exception ex) {
        return new ResponseEntity<>("Ошибка: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
