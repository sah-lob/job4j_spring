package ru.job4j.carssale.persistence.interfaces;

import ru.job4j.carssale.models.Image;

public interface ImageStore {

    void addImg(Image img);
    String getImg(int id);
    int size();
}
