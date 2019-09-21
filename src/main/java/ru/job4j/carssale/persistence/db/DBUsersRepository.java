package ru.job4j.carssale.persistence.db;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.carssale.models.Person;

@Repository
public interface DBUsersRepository extends CrudRepository<Person, Integer> {

    Person getPersonByLogin(String login);

    Person getPersonByLoginAndPassword(String login, String password);
}
