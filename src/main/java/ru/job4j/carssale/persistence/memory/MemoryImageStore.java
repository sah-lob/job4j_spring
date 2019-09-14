package ru.job4j.carssale.persistence.memory;

import org.springframework.stereotype.Component;
import ru.job4j.carssale.models.Image;
import ru.job4j.carssale.persistence.interfaces.ImageStore;

import java.util.HashMap;

@Component
public class MemoryImageStore implements ImageStore {

    private final HashMap<Integer, String> hashMap = new HashMap<>();

    public MemoryImageStore() {
    }

    @Override
    public void addImg(Image image) {
        hashMap.put(image.getCarID(), image.getImage());
    }

    @Override
    public String getImg(int id) {
        return hashMap.get(id);
    }

    @Override
    public int size() {
        return hashMap.size();
    }
}
