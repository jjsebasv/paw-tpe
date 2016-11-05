package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@Rollback
public class ClientJdbcDaoTest {

    private static final String USERNAME = "PAW USER";
    private static final String PASSWORD = "PAWPASS";
    private static final String EMAIL = "asd@email.com";

    @Autowired
    private ClientHibernateDao clientDao;


    @Test
    public void testFindByIdExistingUser() {

        final Client newClient = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL));

        final Client lookupClient = clientDao.findById(newClient.getClientId());

        Assert.assertNotNull(lookupClient);
        Assert.assertEquals(newClient, lookupClient);
    }

    @Test
    public void testFindByIdNonExistingUser() {

        final Client user = clientDao.findById(13);

        Assert.assertNull(user);
    }

    @Test
    public void testCreate() {
        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL));

        Assert.assertNotNull(client);
        Assert.assertEquals(USERNAME, client.getName());
        Assert.assertEquals(PASSWORD, client.getPassword());
        Assert.assertTrue(client.getClientId() >= 0);
    }

    @Test
    public void testFindByUsernameExistingUser() {

        final Client newClient = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL));

        final Client lookupClient = clientDao.findByUsername(newClient.getName());

        Assert.assertNotNull(lookupClient);
        Assert.assertEquals(newClient, lookupClient);
    }

    @Test
    public void testFindByUsernameNonExistingUser() {

        final Client user = clientDao.findByUsername(USERNAME);

        Assert.assertNull(user);
    }

    @Test
    public void testFindByEmailExistingUser() {

        final Client newClient = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL));

        final Client lookupClient = clientDao.findByEmail(newClient.getEmail());

        Assert.assertNotNull(lookupClient);
        Assert.assertEquals(newClient, lookupClient);
        Assert.assertEquals(newClient.getEmail(), lookupClient.getEmail());
    }

    @Test
    public void testFindByEmailNonExistingUser() {

        final Client user = clientDao.findByEmail(EMAIL);

        Assert.assertNull(user);
    }

    @Test
    public void testGetAll() {

        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL));

        final List<Client> list = clientDao.getAll();

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Client lookupClient = list.get(0);

        Assert.assertEquals(client, lookupClient);
    }
}
