package com.example.controller;

import java.time.LocalDate;

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


}
