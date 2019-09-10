package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Function;


@Component
public class DBStorage implements Storage {

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public int addUser(User user) {
        return this.tx(session -> {
            session.save(user);
            return user.getId();
        });
    }

    @Override
    public User getUser(int id) {
        return this.tx(session -> session.get(User.class, id));
    }

    @Override
    public List<User> getAllUsers() {
        return this.tx(session -> session.createQuery("from  User ", User.class).list());
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
