package ru.job4j.intro;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserFactoryTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void whenAddTestBean() {
        var userFactory = context.getBean(UserFactory.class);
        userFactory.addUser(new User("Hello world"));
        userFactory.addUser(new User("Hello world"));
        userFactory.addUser(new User("Hello world"));
        userFactory.addUser(new User("Hello world"));
        assertThat(4, is(userFactory.size()));
    }

    @Test
    public void whenAddUserThanGetThisUser() {
        var userFactory = context.getBean(UserFactory.class);
        var user = new User("Alexander");
        userFactory.addUser(user);
        assertEquals(user, userFactory.getUser(0));
    }

}