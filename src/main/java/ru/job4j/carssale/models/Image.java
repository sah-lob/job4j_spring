package ru.job4j.carssale.models;

import java.util.Objects;

public class Image {

    private int id;
    private int carID;
    private String image;

    public Image(int carID, String image) {
        this.carID = carID;
        this.image = image;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var image1 = (Image) o;
        return id == image1.id
                && carID == image1.carID
                && Objects.equals(image, image1.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carID, image);
    }
}
