package ru.job4j.carssale.models;

import java.util.Objects;

public class BrandAndModel {

    private int id;
    private String brand;
    private String model;

    public BrandAndModel(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public BrandAndModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BrandAndModel that = (BrandAndModel) o;
        return brand.equals(that.brand)
                && model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model);
    }
}
