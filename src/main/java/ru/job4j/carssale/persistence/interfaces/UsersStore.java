package ru.job4j.carssale.persistence.interfaces;

import ru.job4j.carssale.models.Person;


public interface UsersStore {
    void add(Person person);
    boolean isExists(String login);
    boolean validatePerson(Person person);
    boolean editPerson(String login, String fio, String number);
    Person getPerson(String login);
}