package ru.job4j.carssale.persistence.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.CarFilter;
import ru.job4j.carssale.models.Image;
import ru.job4j.carssale.models.Person;
import java.util.List;

@Component
public interface CarStoreController {

    void addData(Car car, String img);
    List<String> getBrands();
    List<String> getModels(String brand);
    void addBrand(String brand, String model);
    int imageSize();
    String getImg(int id);
    void addImg(Image image);
    void addCar(Car car);
    Car findCar(int page, int id);
    int carSize();
    List<Car> carsFindToPage(int page);
    List<Car> carsParamFindPage(CarFilter carFilter);
    int carsGetParamFindPageSize(CarFilter carFilter);
    boolean userIsExists(String login);
    void addPerson(Person person);
    boolean validatePerson(Person person);
    Person getPerson(String login);
    boolean editPerson(String login, String fio, String number);
}
