package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Program;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
//import sun.misc.IOUtils;

import javax.sql.DataSource;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DocumentJdbcDaoTest {

    private static final String COURSE_CODE = "93.71";
    private static final String COURSE_NAME = "Curso de Ejemplo";

    private static final String USERNAME = "PAW USER";
    private static final String PASSWORD = "PAWPASS";
    private static final String EMAIL = "asd@email.com";

    private static final String SUBJECT = "Esto es un subject";
    private static final String FILENAME = "nombre_del_archivo.xyz";

    private static final byte[] CONTENTS = "holaaaaaaaaaaaaaaa".getBytes();
    private static final int FILESIZE = CONTENTS.length;


    @Autowired
    private DataSource ds;

    @Autowired
    private DocumentJdbcDao documentDao;

    @Autowired
    private CourseJdbcDao courseDao;

    @Autowired
    private ClientJdbcDao clientDao;

    @Before
    public void setUp() throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        JdbcTestUtils.deleteFromTables(jdbcTemplate, CourseJdbcDao.COURSETOPROGRAM_TABE_NAME);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, DocumentJdbcDao.DOCUMENT_TABLE_NAME);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, ClientJdbcDao.CLIENT_TABLE_NAME);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, CourseJdbcDao.COURSE_TABLE_NAME);
    }

    @After
    public void tearDown() throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, DocumentJdbcDao.DOCUMENT_TABLE_NAME);

    }

    @Test
    public void testCreate() throws IOException {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final Client client = clientDao.create(USERNAME, PASSWORD, EMAIL);

        final Document document = documentDao.createDocument(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS);

        Assert.assertNotNull(document);
        Assert.assertEquals(course, document.getCourse());
        Assert.assertEquals(client, document.getUser());
        Assert.assertEquals(SUBJECT, document.getSubject());
        Assert.assertEquals(FILENAME, document.getDocumentName());
        Assert.assertEquals(FILESIZE, document.getDocumentSize());

        byte[] rawContents = new byte[(int) document.getDocumentSize()];
        DataInputStream dataIs = new DataInputStream(document.getData());
        dataIs.readFully(rawContents);

        Assert.assertArrayEquals(CONTENTS, rawContents);
    }

    @Test
    public void testFindById() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final Client client = clientDao.create(USERNAME, PASSWORD, EMAIL);

        final Document document = documentDao.createDocument(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS);

        final int id = document.getDocumentId();

        final Document lookupDocument = documentDao.findById(id);

        Assert.assertNotNull(lookupDocument);

        Assert.assertEquals(document, lookupDocument);
    }

    @Test
    public void testFindByNonExistingId() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final Client client = clientDao.create(USERNAME, PASSWORD, EMAIL);

        final Document document = documentDao.createDocument(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS);

        final int id = document.getDocumentId();

        final Document lookupDocument = documentDao.findById(200);

        Assert.assertNull(lookupDocument);
    }


    @Test
    public void testFindByCourse() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final Client client = clientDao.create(USERNAME, PASSWORD, EMAIL);

        final Document document = documentDao.createDocument(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS);

        final List<Document> list = documentDao.findByCourseId(course.getCourseid());

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Document lookupDocument = list.get(0);

        Assert.assertEquals(document, lookupDocument);
    }

    @Test
    public void testFindByClient() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final Client client = clientDao.create(USERNAME, PASSWORD, EMAIL);

        final Document document = documentDao.createDocument(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS);

        final List<Document> list = documentDao.findByClient(client);

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Document lookupDocument = list.get(0);

        Assert.assertEquals(document, lookupDocument);
    }


    @Test
    public void getAll() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final Client client = clientDao.create(USERNAME, PASSWORD, EMAIL);

        final Document document = documentDao.createDocument(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS);

        final List<Document> list = documentDao.getAll();

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Document lookupDocument = list.get(0);

        Assert.assertEquals(document, lookupDocument);
    }

}
