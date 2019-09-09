package ru.job4j.intro;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MemorySorage implements Storage {

    private ArrayList<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    public User get(int id) {
        return users.get(id);
    }

    public int size() {
        return users.size();
    }
}
