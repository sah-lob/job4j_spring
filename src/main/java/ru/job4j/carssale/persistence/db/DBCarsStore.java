package ru.job4j.carssale.persistence.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.job4j.carssale.models.Car;
import ru.job4j.carssale.models.CarFilter;
import ru.job4j.carssale.persistence.interfaces.CarsStore;
import java.util.List;
import java.util.function.Function;

@Component
public class DBCarsStore implements CarsStore {

    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public int add(Car car) {
        this.tx(session -> session.save(car));
        return car.getId();
    }

    @Override
    public Car findCar(int page, int id) {
        return tx(session -> session.get(Car.class, id));
    }

    @Override
    public int size() {
        var hql = "select count(car) from Car car";
        var n = (Number) tx(session -> session.createQuery(hql).uniqueResult());
        return n.intValue();
    }

    @Override
    public List<Car> findToPage(int page) {
        var num = ((page - 1) * 10);
        var list = tx(session -> {
            var q = session.createQuery("FROM Car ORDER BY id DESC");
            q.setFirstResult(num);
            q.setMaxResults(num + 10);
            return q.list();
        });
        return list;
    }

    @Override
    public List<Car> paramFindPage(CarFilter carFilter) {
        var num = ((carFilter.getPage() - 1) * 10);
        var list = tx(session -> {
            var q = session.createQuery("FROM Car WHERE " + createQuery(carFilter) + " ORDER BY id DESC");
            q.setFirstResult(num);
            q.setMaxResults(num + 10);
            return q.list();
        });
       return list;
    }

    @Override
    public int getParamFindPageSize(CarFilter carFilter) {
        var hql = "select count(car) FROM Car car WHERE " + createQuery(carFilter);
        var n = (Number) tx(session -> session.createQuery(hql).uniqueResult());
        return n.intValue();
    }

    public String createQuery(CarFilter carFilter) {
        String string = "";
        var brand = carFilter.getBrand();
        var model = carFilter.getModel();
        var priceFrom = carFilter.getPriceFrom();
        var priceTo = carFilter.getPriceTo();
        var gear = carFilter.getKorobka();
        var powerFrom = carFilter.getPowerFrom();
        var powerTo = carFilter.getPowerTo();
        var yearFrom = carFilter.getYearFrom();
        var yearTo = carFilter.getYearTo();

        if (!brand.equals("-1")) {
            string += addStr("brand", brand);
        }
        if (!model.equals("-1")) {
            string += addStr("model", model);
        }
        if (priceFrom != -1) {
            string += addInt("price", priceFrom, ">=");
        }
        if (priceTo != -1) {
            string += addInt("price", priceTo, "<=");
        }
        if (gear != null && !gear.equals("n") && !gear.equals("undefined")) {
            string += addStr("gear", gear);
        }
        if (powerFrom != -1) {
            string += addInt("power", powerFrom, ">=");
        }
        if (powerTo != -1) {
            string += addInt("power", powerTo, "<=");
        }
        if (yearFrom != -1) {
            string += addInt("year", yearFrom, ">=");
        }
        if (yearTo != -1) {
            string += addInt("year", yearTo, "<=");
        }
        string = string.substring(5);
        return string;
    }

    public String addStr(String param, String value) {
        return " and " + param + "= '" + value + "'";
    }

    public String addInt(String param, int value, String action) {
        return " and " + param + " " + action + " " + value;
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
