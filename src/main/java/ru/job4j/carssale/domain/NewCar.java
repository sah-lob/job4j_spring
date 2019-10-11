package ru.job4j.carssale.domain;

public class NewCar {

    private String string;
    private String brand;
    private String model;
    private String price;
    private String korobka;
    private String power;
    private String year;
    private String fio;
    private String phone;

    public NewCar() {
    }

    public NewCar(String string, String brand, String model, String price, String korobka, String power, String year, String fio, String phone) {
        this.string = string;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.korobka = korobka;
        this.power = power;
        this.year = year;
        this.fio = fio;
        this.phone = phone;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKorobka() {
        return korobka;
    }

    public void setKorobka(String korobka) {
        this.korobka = korobka;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
