package ru.job4j.storage;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MemoryStorage implements Storage {

    private List<User> users = new ArrayList<>();
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public int addUser(User user) {
        user.setId(atomicInteger.getAndIncrement());
        users.add(user);
        return user.getId();
    }

    @Override
    public User getUser(int id) {
        User user = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                user = users.get(i);
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
