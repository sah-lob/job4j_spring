package ru.job4j.carssale.persistence.db;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.carssale.models.Image;

import java.util.List;

public interface DBImagesRepository extends CrudRepository<Image, Integer> {

    @Modifying
    @Query(value = "select * from images where carid = ?", nativeQuery = true)
    List<Image> getImg(int id);
}

