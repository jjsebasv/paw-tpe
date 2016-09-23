package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserJdbcDaoTest {

    private static final String USERNAME = "PAW USER";
    private static final String PASSWORD = "PAWPASS";

    private UserJdbcDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = new UserJdbcDao(null);
    }

    @Test
    public void testFindByIdExistingUser() {
        // Precondiciones: db con un registro para el usuario 42

        // Ejercitacion
        final User user = userDao.findById(42);

        // Postcondiciones
        Assert.assertNotNull("User with id 42 is null!", user);
        Assert.assertEquals("Searched for user 42, got another one", 42, user.getUserid());
    }

    @Test
    public void testFindByIdNonExistingUser() {
        // Precondiciones: db sin un registro para el usuario 13

        // Ejercitacion
        final User user = userDao.findById(13);

        // Postcondiciones
        Assert.assertNull("User with id 42 is null!", user);
    }

    @Test
    public void textCreate() {
        // Precondiciones: db con N registros

        // Ejercitacion
        final User user = userDao.create(USERNAME, PASSWORD);

        // Postcondiciones: db con N+1 registros
        Assert.assertNotNull("The created user had a null pointer", user);
        Assert.assertEquals("The created user has an incorrect name", USERNAME, user.getName());
    }
}
