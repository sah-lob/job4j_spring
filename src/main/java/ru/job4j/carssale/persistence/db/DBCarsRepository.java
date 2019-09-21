package ru.job4j.carssale.persistence.db;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.carssale.models.Car;

import java.util.List;

@Repository
public interface DBCarsRepository extends CrudRepository<Car, Integer> {

    @Modifying
    @Query(value = "select * from cars order by id DESC limit 10 offset ?", nativeQuery = true)
    List<Car> findToPage(int num);

    @Modifying
    @Query(value = "SELECT * FROM cars WHERE brand = ? and model = ? and mechanicgear = ? "
            + "and power >= ? and power <= ? "
            + "and price >= ? and price <= ? "
            + "and year >= ? and year <= ?"
            + " ORDER BY id DESC limit 10 offset ?", nativeQuery = true)
    List<Car> brandModelGear(String brand, String model, boolean gear,
                             int powerFrom, int powerTo, int priceFrom,
                             int priceTO, int yearFrom, int yearTO, int num);

    int countCarsByBrandAndModelAndMechanicGearAndPowerBetweenAndPriceBetweenAndYearBetween(String brand, String model, boolean gear,
                                                                                            int powerFrom, int powerTo, int priceFrom,
                                                                                            int priceTO, int yearFrom, int yearTO);

    @Modifying
    @Query(value = "SELECT * FROM cars WHERE brand = ? and model = ? "
            + "and power >= ? and power <= ? "
            + "and price >= ? and price <= ? "
            + "and year >= ? and year <= ?"
            + " ORDER BY id DESC limit 10 offset ?", nativeQuery = true)
    List<Car> brandModel(String brand, String model,
                             int powerFrom, int powerTo, int priceFrom,
                             int priceTO, int yearFrom, int yearTO, int num);


    int countCarsByBrandAndModelAndPowerBetweenAndPriceBetweenAndYearBetween(String brand, String model,
                                                                             int powerFrom, int powerTo, int priceFrom,
                                                                             int priceTO, int yearFrom, int yearTO);
    @Modifying
    @Query(value = "SELECT * FROM cars WHERE brand = ? and mechanicgear = ? "
            + "and power >= ? and power <= ? "
            + "and price >= ? and price <= ? "
            + "and year >= ? and year <= ?"
            + " ORDER BY id DESC limit 10 offset ?", nativeQuery = true)
    List<Car> brandGear(String brand, boolean gear, int powerFrom, int powerTo, int priceFrom,
                        int priceTO, int yearFrom, int yearTO, int num);

    int countCarsByBrandAndMechanicGearAndPowerBetweenAndPriceBetweenAndYearBetween(String brand, boolean gear,
                                                                                            int powerFrom, int powerTo, int priceFrom,
                                                                                            int priceTO, int yearFrom, int yearTO);
    @Modifying
    @Query(value = "SELECT * FROM cars WHERE brand = ? "
            + "AND power >= ? AND power <= ? "
            + "AND price >= ? AND price <= ? "
            + "AND year >= ? AND year <= ?"
            + " ORDER BY id DESC limit 10 offset ?;", nativeQuery = true)
    List<Car> brand(String brand, int a, int b, int c, int d, int q, int e, int num);

    int countCarsByBrandAndPowerBetweenAndPriceBetweenAndYearBetween(String brand,
                                                                     int powerFrom, int powerTo, int priceFrom,
                                                                     int priceTO, int yearFrom, int yearTO);

    @Modifying
    @Query(value = "SELECT * FROM cars WHERE mechanicgear = ? "
            + "and power >= ? and power <= ? "
            + "and price >= ? and price <= ? "
            + "and year >= ? and year <= ?"
            + " ORDER BY id DESC limit 10 offset ?", nativeQuery = true)
    List<Car> gear(boolean gear, int powerFrom, int powerTo, int priceFrom,
                    int priceTO, int yearFrom, int yearTO, int num);

    int countCarsByMechanicGearAndPowerBetweenAndPriceBetweenAndYearBetween(boolean gear, int powerFrom, int powerTo, int priceFrom,
                                                                                    int priceTO, int yearFrom, int yearTO);

    @Modifying
    @Query(value = "SELECT * FROM cars WHERE "
            + "power >= ? and power <= ? "
            + "and price >= ? and price <= ? "
            + "and year >= ? and year <= ?"
            + " ORDER BY id DESC limit 10 offset ?", nativeQuery = true)
    List<Car> another(int powerFrom, int powerTo, int priceFrom,
                   int priceTO, int yearFrom, int yearTO, int num);

    int countCarsByPowerBetweenAndPriceBetweenAndYearBetween(int powerFrom, int powerTo, int priceFrom,
                                                                            int priceTO, int yearFrom, int yearTO);
}
