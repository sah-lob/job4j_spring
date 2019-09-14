package ru.job4j.carssale.persistence.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.job4j.carssale.models.Image;
import ru.job4j.carssale.persistence.interfaces.ImageStore;
import java.util.function.Function;

@Component
public class DBImageStore implements ImageStore {

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public void addImg(Image image) {
        this.tx(session -> session.save(image));
    }

    @Override
    public String getImg(int id) {
        var sql = "select image from images where car_id = " + id + " limit 1";
        var image = this.tx(session -> session.createSQLQuery(sql).list());
        return String.valueOf(image.get(0));
    }

    @Override
    public int size() {
        var sql = "select count(image) from Image image";
        var n = (Number) tx(session -> session.createQuery(sql).uniqueResult());
        return n.intValue();
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
