package ru.job4j.carssale.persistence.interfaces;

import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.CarFilter;
import java.util.List;

public interface CarsStore {

    int add(Car car);

    Car findCar(int page, int id);

    int size();

    List<Car> findToPage(int page);

    List<Car> paramFindPage(CarFilter carFilter);

    int getParamFindPageSize(CarFilter carFilter);
}
