package com.example.model;

public class VacationData {
    private double averageSalary;
    private int vacationDays;

    public VacationData(double averageSalary, int vacationDays) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
    }

    public double getAverageSalary() {

        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {

        this.averageSalary = averageSalary;
    }

    public int getVacationDays() {

        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {

        this.vacationDays = vacationDays;
    }
}
