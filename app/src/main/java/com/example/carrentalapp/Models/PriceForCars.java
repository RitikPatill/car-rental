package com.example.carrentalapp.Models;

public class PriceForCars {
    int numberOfDays;
    int pricePerDay;

    public PriceForCars(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }


}
