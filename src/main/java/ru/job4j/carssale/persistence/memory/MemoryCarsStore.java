package ru.job4j.carssale.persistence.memory;

import org.springframework.stereotype.Component;
import ru.job4j.carssale.domain.Car;
import ru.job4j.carssale.domain.CarFilter;
import ru.job4j.carssale.domain.MemoryIndexesOnPage;
import ru.job4j.carssale.persistence.interfaces.CarsStore;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class MemoryCarsStore implements CarsStore {

    private final LinkedList<Car> cars = new LinkedList<>();
    private final MemoryIndexesOnPage memoryIndexesOnPage = new MemoryIndexesOnPage();


    @Override
    public int add(Car car) {
        cars.addFirst(car);
        return -1;
    }

    @Override
    public Car findCar(int page, int id) {

        int firstIndex;
        if (memoryIndexesOnPage.getSize() <= 0) {
            firstIndex = ((page - 1) * 10);
        } else {
            firstIndex = memoryIndexesOnPage.getFirstIndexFromPage(page);
            if (firstIndex < 0) {
                firstIndex = 0;
            }
        }
        Car car = null;
        System.out.println(firstIndex);

        for (int i = firstIndex; i < cars.size(); i++) {
            if (cars.get(i).getId() == id) {
                car = cars.get(i);
                break;
            }
        }
        return car;
    }

    @Override
    public int size() {
        return cars.size();
    }

    @Override
    public List<Car> findToPage(int page) {
        ArrayList<Car> cars2 = new ArrayList<>();
        var num = ((page - 1) * 10);
        var to = num + 10;
        if ((num + 10) >= cars.size()) {
            to = cars.size();
        }
        for (int i = num; i < to; i++) {
            cars2.add(cars.get(i));
        }
        System.out.println(cars2.get(0).getPrice());
        return cars2;
    }

    @Override
    public List<Car> paramFindPage(CarFilter carFilter) {
        var page = carFilter.getPage();
        var carID = carFilter.getCarId();
        if (memoryIndexesOnPage.containsKey(page)) {
            carID = memoryIndexesOnPage.getFirstIndexFromPage(page);
        } else {
            carID = carFilter.getCarId();
        }
        var brand = carFilter.getBrand();
        var model = carFilter.getModel();
        var priceFrom = carFilter.getPriceFrom();
        var priceTo = carFilter.getPriceTo();
        var gear = carFilter.getKorobka();
        var powerFrom = carFilter.getPowerFrom();
        var powerTo = carFilter.getPowerTo();
        var yearFrom = carFilter.getYearFrom();
        var yearTo = carFilter.getYearTo();
        var list = new ArrayList<Car>();
        var num = 0;

        for (var c : cars) {
            if (c.getId() > carID) {
                var result = true;
                if (!brand.equals("-1")) {
                    if (!c.getBrand().equals(brand)) {
                        continue;
                    }
                }

                if (!model.equals("-1")) {
                    if (!c.getModel().equals(model)) {
                        continue;
                    }
                }
                if (priceFrom != -1) {
                    if (c.getPrice() < priceFrom) {
                        continue;
                    }
                }
                if (priceTo != -1) {
                    if (c.getPrice() > priceTo) {
                        continue;
                    }
                }
                if (gear != null) {
                    if (!gear.equals("n")) {
                        if (gear.equals("m") && !c.isMechanicGear()) {
                            continue;
                        } else if (gear.equals("a") && c.isMechanicGear()) {
                            continue;
                        }
                    }
                }
                if (powerFrom != -1) {
                    if (c.getPower() < powerFrom) {
                        continue;
                    }
                }
                if (powerTo != -1) {
                    if (c.getPower() > powerTo) {
                        continue;
                    }
                }
                if (yearFrom != -1) {
                    if (c.getYear() < yearFrom) {
                        continue;
                    }
                }
                if (yearTo != -1) {
                    if (c.getYear() > yearTo) {
                        continue;
                    }
                }
                if (num < 10) {
                    list.add(c);
                }
                num++;
            }
        }
        int listSize;
        if (list.size() > 0) {
            listSize = list.get(list.size() - 1).getId();
        } else {
            listSize = 0;
        }
        memoryIndexesOnPage.addPage(page, carID, listSize);

        if (carID < 0) {
            memoryIndexesOnPage.setSize(num + 1);
        }
//        testParamFindPageSize = num + 1;
        return list;
    }

    @Override
    public int getParamFindPageSize(CarFilter carFilter) {
        return memoryIndexesOnPage.getSize();
    }
}
