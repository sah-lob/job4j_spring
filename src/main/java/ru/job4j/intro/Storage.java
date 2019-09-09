package ru.job4j.intro;

public interface Storage {
    void add(User user);
    User get(int id);
    int size();
}
