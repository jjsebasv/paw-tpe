package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ClientJdbcDaoTest {

    private static final String USERNAME = "PAW USER";
    private static final String PASSWORD = "PAWPASS";
    private static final String EMAIL = "asd@email.com";

    @Autowired
    private DataSource ds;

    @Autowired
    private ClientJdbcDao clientDao;

    @Before
    public void setUp() throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        JdbcTestUtils.deleteFromTables(jdbcTemplate, ClientJdbcDao.CLIENT_TABLE_NAME);
    }

    @Test
    public void testFindByIdExistingUser() {

        final Client newClient = clientDao.create(USERNAME, PASSWORD, EMAIL);

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
        final Client client = clientDao.create(USERNAME, PASSWORD, EMAIL);

        Assert.assertNotNull(client);
        Assert.assertEquals(USERNAME, client.getName());
        Assert.assertEquals(PASSWORD, client.getPassword());
        Assert.assertTrue(client.getClientId() >= 0);
    }

    @Test
    public void testFindByUsernameExistingUser() {

        final Client newClient = clientDao.create(USERNAME, PASSWORD, EMAIL);

        final Client lookupClient = clientDao.findByUsername(newClient.getName());

        Assert.assertNotNull(lookupClient);
        Assert.assertEquals(newClient, lookupClient);
    }

    @Test
    public void testFindByUsernameNonExistingUser() {

        final Client user = clientDao.findByUsername(USERNAME);

        Assert.assertNull(user);
    }
}
