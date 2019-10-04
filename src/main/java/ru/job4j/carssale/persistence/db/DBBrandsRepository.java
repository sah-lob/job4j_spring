package ru.job4j.carssale.persistence.db;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.carssale.domain.BrandAndModel;

import java.util.List;

@Repository
public interface DBBrandsRepository extends CrudRepository<BrandAndModel, Integer> {

    @Modifying
    @Query(value = "SELECT DISTINCT brand FROM brands", nativeQuery = true)
    List<String> getBrands();

    @Modifying
    @Query(value = "SELECT DISTINCT model FROM brands WHERE brand = ?", nativeQuery = true)
    List<String> getModels(String brand);

    int countBrandAndModelByBrand(String brand);

    int countBrandAndModelByModel(String model);
}
