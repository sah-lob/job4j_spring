package ru.job4j.carssale.persistence.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.carssale.domain.*;
import ru.job4j.carssale.persistence.interfaces.*;
import java.util.List;

@Component
public class DBCarStoreController implements CarStoreController {

    @Autowired
    private DBBrandsRepository brandStore;

    @Autowired
    private DBCarsRepository carsStore;

    @Autowired
    private DBImagesRepository imageStore;

    @Autowired
    private DBUsersRepository usersStore;


    @Override
    public void addData(Car car, String img) {
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
        if (brandStore.countBrandAndModelByBrand(brand) == 0 && brandStore.countBrandAndModelByModel(model) == 0) {

            brandStore.save(new BrandAndModel(brand, model));
        }
    }

    //ImageStoree

    @Override
    public int imageSize() {
        return (int) imageStore.count();
    }

    @Override
    public String getImg(int id) {
        return imageStore.getImg(id).get(0).getImage();
    }

    @Override
    public void addImg(Image image) {
        imageStore.save(image);
    }

    //CarsStore

    @Override
    public void addCar(Car car) {
        carsStore.save(car);
    }

    @Override
    public Car findCar(int page, int id) {
        return carsStore.findById(id).get();
    }

    @Override
    public int carSize() {
        return (int) carsStore.count();
    }

    @Override
    public List<Car> carsFindToPage(int page) {
        var num = ((page - 1) * 10);
        return  carsStore.findToPage(num);
    }

    @Override
    public List<Car> carsParamFindPage(CarFilter carFilter) {
        List<Car> list;
        int num = ((carFilter.getPage() - 1) * 10);
        System.out.println(num);
        String brand = carFilter.getBrand();
        String model = carFilter.getModel();
        int priceFrom = carFilter.getPriceFrom();
        int priceTo = carFilter.getPriceTo();
        String korobka = carFilter.getKorobka();
        int powerFrom = carFilter.getPowerFrom();
        int powerTo = carFilter.getPowerTo();
        int yearFrom = carFilter.getYearFrom();
        int yearTo = carFilter.getYearTo();

        if (priceTo == -1) {
            priceTo = Integer.MAX_VALUE;
        }
        if (powerTo == -1) {
            powerTo = Integer.MAX_VALUE;
        }
        if (yearTo == -1) {
            yearTo = Integer.MAX_VALUE;
        }
        boolean selectedGear = korobka != null && !korobka.equals("n") && !korobka.equals("undefined");
        if (!brand.equals("-1")) {
            if (!model.equals("-1")) {
                if (selectedGear) {
                    boolean gear = false;
                    if (korobka.equals("m")) {
                        gear = true;
                    }
                    list = carsStore.brandModelGear(brand, model, gear, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo, num);
                } else {
                    list = carsStore.brandModel(brand, model, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo, num);
                }
            } else {
                //model
                if (selectedGear) {
                    boolean gear = false;
                    if (korobka.equals("m")) {
                        gear = true;
                    }
                    list = carsStore.brandGear(brand, gear, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo, num);
                } else {
                    list = carsStore.brand(brand, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo, num);
                    System.out.println(brand);
                }
            }
        } else {
            if (selectedGear) {
                boolean gear = false;
                if (korobka.equals("m")) {
                    gear = true;
                }
                list =  carsStore.gear(gear, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo, num);
            } else {
                list = carsStore.another(powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo, num);
            }
        }
        return list;
    }

    @Override
    public int carsGetParamFindPageSize(CarFilter carFilter) {
        var brand = carFilter.getBrand();
        var model = carFilter.getModel();
        var priceFrom = carFilter.getPriceFrom();
        var priceTo = carFilter.getPriceTo();
        var korobka = carFilter.getKorobka();
        var powerFrom = carFilter.getPowerFrom();
        var powerTo = carFilter.getPowerTo();
        var yearFrom = carFilter.getYearFrom();
        var yearTo = carFilter.getYearTo();
        var size = 0;

        if (priceTo == -1) {
            priceTo = Integer.MAX_VALUE;
        }
        if (powerTo == -1) {
            powerTo = Integer.MAX_VALUE;
        }
        if (yearTo == -1) {
            yearTo = Integer.MAX_VALUE;
        }
        boolean selectedGear = korobka != null && !korobka.equals("n") && !korobka.equals("undefined");
        if (!brand.equals("-1")) {
            if (!model.equals("-1")) {
                if (selectedGear) {
                    boolean gear = false;
                    if (korobka.equals("m")) {
                        gear = true;
                    }
                    size = carsStore.countCarsByBrandAndModelAndMechanicGearAndPowerBetweenAndPriceBetweenAndYearBetween(
                            brand, model, gear, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo);
                } else {
                    size = carsStore.countCarsByBrandAndModelAndPowerBetweenAndPriceBetweenAndYearBetween(
                            brand, model, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo);
                }
            } else {
                if (selectedGear) {
                    boolean gear = false;
                    if (korobka.equals("m")) {
                        gear = true;
                    }
                    size = carsStore.countCarsByBrandAndMechanicGearAndPowerBetweenAndPriceBetweenAndYearBetween(
                            brand, gear, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo);
                } else {
                    size = carsStore.countCarsByBrandAndPowerBetweenAndPriceBetweenAndYearBetween(
                            brand, powerFrom, powerTo, priceFrom, priceTo, yearFrom, yearTo);
                }
            }
        } else {
            if (selectedGear) {
                boolean gear = false;
                if (korobka.equals("m")) {
                    gear = true;
                }
                size = carsStore.countCarsByMechanicGearAndPowerBetweenAndPriceBetweenAndYearBetween(gear, powerFrom, powerTo, priceFrom,
                        priceTo, yearFrom, yearTo);
            } else {
                size = carsStore.countCarsByPowerBetweenAndPriceBetweenAndYearBetween(powerFrom, powerTo, priceFrom,
                        priceTo, yearFrom, yearTo);
            }
        }

        System.out.println(size);
        return size;
    }

    //UsersStore

    @Override
    public boolean userIsExists(String login) {
        return usersStore.getFirstPersonByLogin(login) != null;
    }

    @Override
    public void addPerson(Person person) {
        usersStore.save(person);
    }

    @Override
    public boolean validatePerson(Person person) {
        return usersStore.getPersonByLoginAndPassword(person.getLogin(), person.getPassword()) != null;
    }

    @Override
    public Person getPerson(String login) {
        return usersStore.getFirstPersonByLogin(login);
    }

    @Override
    public boolean editPerson(String login, String fio, String number) {
        Person person = usersStore.getFirstPersonByLogin(login);
        person.setFio(fio);
        person.setPhone(number);
        usersStore.save(person);
        return true;
    }
}
