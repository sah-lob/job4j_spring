package ru.job4j.carssale.models;

import java.util.Objects;

public class Car {

    private int id;
    private String brand;
    private String model;
    private int price;
    private boolean mechanicGear;
    private int power;
    private int year;
    private String owner;


    public Car(String brand, String model, int price, boolean mechanicGear, int power, int year, String owner) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.mechanicGear = mechanicGear;
        this.power = power;
        this.year = year;
        this.owner = owner;
    }

    public Car(String brand, String model, String price, String mechanicGear, String power, String year, String owner) {
        this.brand = brand;
        this.model = model;
        this.price = Integer.valueOf(price);
        this.mechanicGear = mechanicGear.equals("m");
        this.power = Integer.valueOf(power);
        this.year = Integer.valueOf(year);
        this.owner = owner;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isMechanicGear() {
        return mechanicGear;
    }

    public void setMechanicGear(boolean mechanicGear) {
        this.mechanicGear = mechanicGear;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return price == car.price
                && mechanicGear == car.mechanicGear
                && power == car.power
                && year == car.year
                && Objects.equals(brand, car.brand)
                && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, price, mechanicGear, power, year);
    }
}
