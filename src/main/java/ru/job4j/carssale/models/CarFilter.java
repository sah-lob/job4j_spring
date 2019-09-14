package ru.job4j.carssale.models;

import java.util.ArrayList;
import java.util.List;

public class CarFilter {

    boolean filter;
    private int page;
    private int carId;
    private String brand;
    private String model;
    private int priceFrom;
    private int priceTo;
    private String korobka;
    private int powerFrom;
    private int powerTo;
    private int yearFrom;
    private int yearTo;

    public CarFilter(List<String> params) {

        var page = params.get(0);
        var carID = params.get(1);
        var brand = params.get(2);
        var model = params.get(3);
        var priceFrom = params.get(4);
        var priceTo = params.get(5);
        var korobka = params.get(6);
        var powerFrom = params.get(7);
        var powerTo = params.get(8);
        var yearFrom = params.get(9);
        var yearTo = params.get(10);

        this.page = Integer.valueOf(page);
        String un = "";
        if (brand.equals(un) && model.equals(un)
                && priceFrom.equals(un) && priceTo.equals(un)
                && (korobka.equals(un) || korobka.equals("none"))
                && powerFrom.equals(un) && powerTo.equals(un)
                && yearFrom.equals(un) && yearTo.equals(un)) {
            filter = false;
        } else {
            filter = true;
            this.carId = Integer.valueOf(carID);
            if (brand.equals(un)) {
                this.brand = null;
            } else {
                this.brand = brand;
            }
            this.model = model;
            if (priceFrom.equals(un)) {
                this.priceFrom = -1;
            } else {
                this.priceFrom = Integer.valueOf(priceFrom);
            }
            if (priceTo.equals(un)) {
                this.priceTo = -1;
            } else {
                this.priceTo = Integer.valueOf(priceTo);
            }
            if (korobka.equals(un) || korobka.equals("none")) {
                this.korobka = "n";
            } else {
                this.korobka = korobka;
            }
            if (powerFrom.equals(un)) {
                this.powerFrom = -1;
            } else {
                this.powerFrom = Integer.valueOf(powerFrom);
            }
            if (powerTo.equals(un)) {
                this.powerTo = -1;
            } else {
                this.powerTo = Integer.valueOf(powerTo);
            }
            if (yearFrom.equals(un)) {
                this.yearFrom = -1;
            } else {
                this.yearFrom = Integer.valueOf(yearFrom);
            }
            if (yearTo.equals(un)) {
                this.yearTo = -1;
            } else {
                this.yearTo = Integer.valueOf(yearTo);
            }
        }

    }

    private boolean equals(String string) {
        return string.equals("") || string.equals("none") || string.equals("undefined");
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public int getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(int priceFrom) {
        this.priceFrom = priceFrom;
    }

    public int getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(int priceTo) {
        this.priceTo = priceTo;
    }

    public String getKorobka() {
        return korobka;
    }

    public void setKorobka(String korobka) {
        this.korobka = korobka;
    }

    public int getPowerFrom() {
        return powerFrom;
    }

    public void setPowerFrom(int powerFrom) {
        this.powerFrom = powerFrom;
    }

    public int getPowerTo() {
        return powerTo;
    }

    public void setPowerTo(int powerTo) {
        this.powerTo = powerTo;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
