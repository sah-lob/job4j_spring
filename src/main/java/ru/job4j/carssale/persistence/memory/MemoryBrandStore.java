package ru.job4j.carssale.persistence.memory;
import org.springframework.stereotype.Component;
import ru.job4j.carssale.persistence.interfaces.BrandStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class MemoryBrandStore implements BrandStore {

    private HashMap<String, ArrayList<String>> brands = new HashMap<>();


    public MemoryBrandStore() {
    }

    @Override
    public boolean delete(String brand, String model) {
        var result = true;
        if (!brands.containsKey(brand)) {
            result = false;
        } else {
            var list = brands.get(brand);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(model)) {
                    list.remove(i);
                    break;
                }
            }
            if (list.size() == 0) {
                brands.remove(brand);
            }
        }
        return result;
    }

    @Override
    public void add(String brand, String model) {
        if (brands.containsKey(brand)) {
            var list = brands.get(brand);
            var result = true;
            for (var l: list) {
                if (l.equals(model)) {
                    result = false;
                    break;
                }
            }
            if (result) {
                list.add(model);
                brands.put(brand, list);
            }
        } else {
            var list = new ArrayList<String>();
            list.add(model);
            brands.put(brand, list);
        }

    }

    @Override
    public List<String> getBrands() {
        var matchingKeys = new ArrayList<String>();
        for (var key : brands.keySet()) {
                matchingKeys.add(key);
        }
        return matchingKeys;
    }

    @Override
    public List<String> getModels(String brand) {
        return brands.get(brand);
    }
}
