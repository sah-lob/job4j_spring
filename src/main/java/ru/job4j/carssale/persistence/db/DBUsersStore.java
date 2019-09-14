package ru.job4j.carssale.persistence.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.job4j.carssale.models.Person;
import ru.job4j.carssale.persistence.interfaces.UsersStore;

import java.util.List;
import java.util.function.Function;

@Component
public class DBUsersStore implements UsersStore {

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public void add(Person person) {
        this.tx(session -> session.save(person));
    }

    @Override
    public boolean isExists(String login) {
        var sql = "select * from persons where login = '" + login + "' limit 1";
        List t = this.tx(session -> session.createSQLQuery(sql).list());
        return t.size() > 0;
    }

    @Override
    public boolean validatePerson(Person person) {
        var sql = "select 1 from persons where login = '" + person.getLogin() + "' and password = '" + person.getPassword() + "' limit 1";
        List t = this.tx(session -> session.createSQLQuery(sql).list());
        return t.size() > 0;
    }

    @Override
    public boolean editPerson(String login, String fio, String number) {
        var sql = "UPDATE  persons SET phone = '" + number + "', fio = '" + fio + "' WHERE login = '" + login + "'";
        this.tx(session -> session.createSQLQuery(sql).executeUpdate());
        return false;
    }

    @Override
    public Person getPerson(String login) {
        var pql = "From Person where login = '" + login + "'";
        var person = tx(session -> session.createQuery(pql, Person.class).list()).get(0);
        return person;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
