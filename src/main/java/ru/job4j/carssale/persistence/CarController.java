package ru.job4j.carssale.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.job4j.carssale.domain.Car;
import ru.job4j.carssale.domain.CarFilter;
import ru.job4j.carssale.domain.Image;
import ru.job4j.carssale.domain.Person;
import ru.job4j.carssale.persistence.interfaces.CarStoreController;

import java.util.List;

//@Service
@Component
public class CarController implements CarStoreController {

    @Autowired
    @Qualifier("DBCarStoreController")
    private CarStoreController carStoreController;

    @Override
    public void addData(Car car, String img) {
        carStoreController.addData(car, img);
    }

    @Override
    public List<String> getBrands() {
        return carStoreController.getBrands();
    }

    @Override
    public List<String> getModels(String brand) {
        return carStoreController.getModels(brand);
    }

    @Override
    public void addBrand(String brand, String model) {
        carStoreController.addBrand(brand, model);
    }

    @Override
    public int imageSize() {
        return carStoreController.imageSize();
    }

    @Override
    public String getImg(int id) {
        return carStoreController.getImg(id);
    }

    @Override
    public void addImg(Image image) {
        carStoreController.addImg(image);
    }

    @Override
    public void addCar(Car car) {
        carStoreController.addCar(car);
    }

    @Override
    public Car findCar(int page, int id) {
        return carStoreController.findCar(page, id);
    }

    @Override
    public int carSize() {
        return carStoreController.carSize();
    }

    @Override
    public List<Car> carsFindToPage(int page) {
        return carStoreController.carsFindToPage(page);
    }

    @Override
    public List<Car> carsParamFindPage(CarFilter carFilter) {
        return carStoreController.carsParamFindPage(carFilter);
    }

    @Override
    public int carsGetParamFindPageSize(CarFilter carFilter) {
        return carStoreController.carsGetParamFindPageSize(carFilter);
    }

    @Override
    public boolean userIsExists(String login) {
        return carStoreController.userIsExists(login);
    }

    @Override
    public void addPerson(Person person) {
        carStoreController.addPerson(person);
    }

    @Override
    public boolean validatePerson(Person person) {
        return carStoreController.validatePerson(person);
    }

    @Override
    public Person getPerson(String login) {
        return carStoreController.getPerson(login);
    }

    @Override
    public boolean editPerson(String login, String fio, String number) {
        return carStoreController.editPerson(login, fio, number);
    }
}
