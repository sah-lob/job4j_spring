package ru.job4j.storage;

import java.util.List;

public interface Storage {

    int addUser(User user);

    User getUser(int id);

    List<User> getAllUsers();
}
