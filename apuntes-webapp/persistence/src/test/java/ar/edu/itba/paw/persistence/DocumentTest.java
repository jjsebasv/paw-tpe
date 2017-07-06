package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@Rollback
public class DocumentTest {

    private static final String PROGRAM_NAME = "Carrera de ejemplo";
    private static final String PROGRAM_SHORTNAME = "ejemplo";
    private static final char PROGRAM_GROUP = 'g';

    private static final String COURSE_CODE = "93.71";
    private static final String COURSE_NAME = "Curso de Ejemplo";

    private static final String USERNAME = "PAW USER";
    private static final String PASSWORD = "PAWPASS";
    private static final String EMAIL = "asd@email.com";
    private static final ClientRole ROLE = ClientRole.ROLE_USER;
    private static final String RECOVERY_QUESTION = "My name?";
    private static final String SECRET_ANSWER = "Who cares!";

    private static final String SUBJECT = "Esto es un subject";
    private static final String FILENAME = "nombre_del_archivo.xyz";

    private static final byte[] CONTENTS = "holaaaaaaaaaaaaaaa".getBytes();
    private static final int FILESIZE = CONTENTS.length;

    private static final String DESCRIPTION = "estaesladescripcion";

    private static final String UNIVERSITY_NAME = "ITBA";
    private static final String UNIVERSITY_DOMAIN = "itba.edu.ar";

    @Autowired
    private DocumentHibernateDao documentDao;

    @Autowired
    private CourseHibernateDao courseDao;

    @Autowired
    private ClientHibernateDao clientDao;

    @Autowired
    private UniversityHibernateDao universityDao;

    @Autowired
    private ProgramHibernateDao programDao;

    @Test
    public void testCreate() throws IOException {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL, ROLE, university, RECOVERY_QUESTION, SECRET_ANSWER, program));

        final Document document = documentDao.create(new Document(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS, DESCRIPTION));

        Assert.assertNotNull(document);
        Assert.assertEquals(course, document.getCourse());
        Assert.assertEquals(client, document.getUser());
        Assert.assertEquals(SUBJECT, document.getSubject());
        Assert.assertEquals(FILENAME, document.getDocumentName());
        Assert.assertEquals(FILESIZE, document.getDocumentSize());
        Assert.assertNotNull(document.getDateUploaded());
        Assert.assertEquals(DESCRIPTION, document.getDescription());

        byte[] rawContents = new byte[(int) document.getDocumentSize()];

        Assert.assertArrayEquals(CONTENTS, document.getData());
    }

    @Test
    public void testFindById() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL, ROLE, university, RECOVERY_QUESTION, SECRET_ANSWER, program));

        final Document document = documentDao.create(new Document(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS, DESCRIPTION));

        final long id = document.getDocumentId();

        final Document lookupDocument = documentDao.findById(id);

        Assert.assertNotNull(lookupDocument);

        Assert.assertEquals(document, lookupDocument);
    }

    @Test
    public void testFindByNonExistingId() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL, ROLE, university, RECOVERY_QUESTION, SECRET_ANSWER, program));

        final Document document = documentDao.create(new Document(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS, DESCRIPTION));

        final long id = document.getDocumentId();

        final Document lookupDocument = documentDao.findById(200);

        Assert.assertNull(lookupDocument);
    }


    @Test
    public void testFindByCourse() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL, ROLE, university, RECOVERY_QUESTION, SECRET_ANSWER, program));

        final Document document = documentDao.create(new Document(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS, DESCRIPTION));

        final List<Document> list = documentDao.findByCourseId(course.getCourseid());

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Document lookupDocument = list.get(0);

        Assert.assertEquals(document, lookupDocument);
    }

    @Test
    public void testFindByClient() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL, ROLE, university, RECOVERY_QUESTION, SECRET_ANSWER, program));

        final Document document = documentDao.create(new Document(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS, DESCRIPTION));

        final List<Document> list = documentDao.findByClientId(client.getClientId());

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Document lookupDocument = list.get(0);

        Assert.assertEquals(document, lookupDocument);
    }


    @Test
    public void getAll() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final Client client = clientDao.create(new Client(USERNAME, PASSWORD, EMAIL, ROLE, university, RECOVERY_QUESTION, SECRET_ANSWER, program));

        final Document document = documentDao.create(new Document(client, course, SUBJECT, FILENAME, FILESIZE, CONTENTS, DESCRIPTION));

        final List<Document> list = documentDao.getAll();

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Document lookupDocument = list.get(0);

        Assert.assertEquals(document, lookupDocument);
    }

}
