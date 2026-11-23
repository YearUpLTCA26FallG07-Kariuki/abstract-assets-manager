package com.pluralsight;

import java.math.BigDecimal;

public class Vehicle extends Asset {

    private String makeModel;
    private int year;
    private int odometer;

    public Vehicle(String description, String dateAcquired, BigDecimal originalCost,
                   String makeModel, int year, int odometer) {

        super(description, dateAcquired, originalCost);

        this.makeModel = makeModel;
        this.year = year;
        this.odometer = odometer;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public int getYear() {
        return year;
    }

    public int getOdometer() {
        return odometer;
    }

    @Override
    public BigDecimal getValue() {

        int age = java.time.Year.now().getValue() - year;

        BigDecimal value = getOriginalCost();

        // applies depreciation based on the vehicle's age
        if (age <= 3) {
            value = value.multiply(new BigDecimal("0.97"));
        } else if (age <= 6) {
            value = value.multiply(new BigDecimal("0.94"));
        } else if (age <= 10) {
            value = value.multiply(new BigDecimal("0.92"));
        } else {
            value = value.subtract(new BigDecimal("1000"));
        }

        // applies a mileage reduction unless the vehicle is a Honda or Toyota
        if (odometer > 100000
                && !makeModel.contains("Honda")
                && !makeModel.contains("Toyota")) {
            value = value.multiply(new BigDecimal("0.75"));
        }

        return value;
    }
}