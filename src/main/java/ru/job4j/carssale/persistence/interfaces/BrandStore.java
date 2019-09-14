package ru.job4j.carssale.persistence.interfaces;
import java.util.List;

public interface BrandStore {

    boolean delete(String brand, String model);

    void add(String brand, String model);

    List<String> getBrands();

    List<String> getModels(String brand);
}
