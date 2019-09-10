package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImportUser {

    private final Storage storage;

    @Autowired
    public ImportUser(@Qualifier("memoryStorage") Storage storage) {
        this.storage = storage;
    }

    public int addUser(User user) {
        return storage.addUser(user);
    }

    public User getUser(int id) {
        return storage.getUser(id);
    }

    public List<User> getAllUsers() {
        return storage.getAllUsers();
    }
}
