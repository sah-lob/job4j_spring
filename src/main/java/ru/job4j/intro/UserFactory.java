package ru.job4j.intro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    Storage storage;

    @Autowired
    public UserFactory(Storage storage) {
        this.storage = storage;
    }

    public void addUser(User user) {
        storage.add(user);
    }

    public User getUser(int id) {
        return storage.get(id);
    }

    public int size() {
        return storage.size();
    }
}
