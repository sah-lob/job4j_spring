package ru.job4j.carssale.persistence.db;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.carssale.domain.Person;

@Repository
public interface DBUsersRepository extends CrudRepository<Person, Integer> {

    Person getFirstPersonByLogin(String login);

    Person getPersonByLoginAndPassword(String login, String password);
}
