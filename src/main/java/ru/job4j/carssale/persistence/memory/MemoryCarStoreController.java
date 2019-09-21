package ru.job4j.carssale.persistence.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.CarFilter;
import ru.job4j.carssale.models.Image;
import ru.job4j.carssale.models.Person;
import ru.job4j.carssale.persistence.interfaces.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MemoryCarStoreController implements CarStoreController {


    @Autowired
    private MemoryBrandStore brandStore;
    @Autowired
    private MemoryCarsStore carsStore;
    @Autowired
    private MemoryImageStore imageStore;
    @Autowired
    private MemoryUsersStore usersStore;
    private AtomicInteger id = new AtomicInteger(0);

    @Override
    public void addData(Car car, String img) {
        car.setId(id.getAndIncrement());
        addCar(car);
        addImg(new Image(car.getId(), img));
        addBrand(car.getBrand(), car.getModel());
    }

    // BrandStore

    @Override
    public List<String> getBrands() {
        return brandStore.getBrands();
    }

    @Override
    public List<String> getModels(String brand) {
        return brandStore.getModels(brand);
    }

    @Override
    public void addBrand(String brand, String model) {
        brandStore.add(brand, model);
    }

    //ImageStore

    @Override
    public int imageSize() {
        return imageStore.size();
    }

    @Override
    public String getImg(int id) {
        return imageStore.getImg(id);
    }

    @Override
    public void addImg(Image image) {
        imageStore.addImg(image);
    }

    //CarsStore

    @Override
    public void addCar(Car car) {
        carsStore.add(car);
    }

    @Override
    public Car findCar(int page, int id) {
        return carsStore.findCar(page, id);
    }

    @Override
    public int carSize() {
        return carsStore.size();
    }

    @Override
    public List<Car> carsFindToPage(int page) {
        return carsStore.findToPage(page);
    }

    @Override
    public List<Car> carsParamFindPage(CarFilter carFilter) {
        return carsStore.paramFindPage(carFilter);
    }

    @Override
     public int carsGetParamFindPageSize(CarFilter carFilter) {
        return carsStore.getParamFindPageSize(carFilter);
     }

    //UsersStore

    @Override
    public boolean userIsExists(String login) {
        return usersStore.isExists(login);
    }

    @Override
    public void addPerson(Person person) {
        usersStore.add(person);
    }

    @Override
    public boolean validatePerson(Person person) {
        return usersStore.validatePerson(person);
    }

    @Override
    public Person getPerson(String login) {
        return usersStore.getPerson(login);
    }

    @Override
    public boolean editPerson(String login, String fio, String number) {
        return usersStore.editPerson(login, fio, number);
    }

//    public void testList(int size) {
//        if (carSize() == 0) {
//            for (int i = 0; i < size; i++) {
//                if (i % 2 == 0) {
//                    FileInputStream inputStream = null;
//                    try {
//                        inputStream = new FileInputStream("/Users/alexanderlobachev/Desktop/a.png");
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    byte[] buffer = new byte[4096];
//                    int bytesRead = -1;
//                    while (true) {
//                        try {
//                            bytesRead = inputStream.read(buffer);
//                            if (!(bytesRead != -1)) {
//                                break;
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        outputStream.write(buffer, 0, bytesRead);
//                    }
//                    byte[] imageBytes = outputStream.toByteArray();
//                    try {
//                        inputStream.close();
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    String string = Base64.getEncoder().encodeToString(imageBytes);
//                    Person person = new Person();
//                    person.setLogin("loh");
//                    person.setFio("Александр Лобачев");
//                    person.setPassword("123");
//                    person.setPhone("8-916-633-58-00");
//                    Car car = new Car("Porshe", "Boxter",  6000000 + i, true, 650 + i, 2018 + i, person.getLogin());
//                    addPerson(person);
//                    addData(car, string);
//                } else {
//                    FileInputStream inputStream = null;
//                    try {
//                        inputStream = new FileInputStream("/Users/alexanderlobachev/Desktop/b.png");
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    byte[] buffer = new byte[4096];
//                    int bytesRead = -1;
//                    while (true) {
//                        try {
//                            bytesRead = inputStream.read(buffer);
//                            if (!(bytesRead != -1)) {
//                                break;
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        outputStream.write(buffer, 0, bytesRead);
//                    }
//                    byte[] imageBytes = outputStream.toByteArray();
//                    try {
//                        inputStream.close();
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    String string = Base64.getEncoder().encodeToString(imageBytes);
//                    Person person = new Person();
//                    person.setLogin("loh");
//                    person.setFio("Anna Inshakova");
//                    person.setPassword("123");
//                    person.setPhone("8-916-633-58-00");
//                    Car car = new Car("Ford", "Mondeo",  6000000 + i, false, 650 + i, 2018 + i, person.getLogin());
//                    addPerson(person);
//                    addData(car, string);
//                }
//            }
//        }
//    }
}
