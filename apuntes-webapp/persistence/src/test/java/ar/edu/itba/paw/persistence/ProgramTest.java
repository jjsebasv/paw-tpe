package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Program;
import ar.edu.itba.paw.models.University;
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
public class ProgramTest {

    private static final String PROGRAM_NAME = "Carrera de ejemplo";
    private static final String PROGRAM_SHORTNAME = "ejemplo";
    private static final char PROGRAM_GROUP = 'g';

    private static final String UNIVERSITY_NAME = "ITBA";
    private static final String UNIVERSITY_DOMAIN = "itba.edu.ar";


    @Autowired
    private ProgramHibernateDao programDao;

    @Autowired
    private UniversityHibernateDao universityDao;

    @Test
    public void testCreate() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));

        Assert.assertNotNull(program);
        Assert.assertEquals(PROGRAM_NAME, program.getName());
        Assert.assertEquals(PROGRAM_SHORTNAME, program.getShortName());
        Assert.assertEquals(PROGRAM_GROUP, program.getGroup());
    }

    @Test
    public void testFindByName() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));

        final List<Program> list = programDao.findByName(PROGRAM_NAME);

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Program lookupProgram = list.get(0);

        Assert.assertEquals(program, lookupProgram);
    }

    @Test
    public void testFindByNonExistingName() {
        final List<Program> list = programDao.findByName("abc");

        Assert.assertNull(list);
    }

    @Test
    public void testFindById() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final long id = program.getProgramid();

        final Program lookupProgram = programDao.findById(id);

        Assert.assertNotNull(lookupProgram);

        Assert.assertEquals(program, lookupProgram);
    }

    @Test
    public void testFindByNonExistingId() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));
        final long id = program.getProgramid();

        final Program lookupProgram = programDao.findById(200);

        Assert.assertNull(lookupProgram);
    }

    @Test
    public void getAll() {
        final University university = universityDao.create(new University(UNIVERSITY_NAME, UNIVERSITY_DOMAIN));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP, university));

        final List<Program> list = programDao.getAll();

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Program lookupProgram = list.get(0);

        Assert.assertEquals(program, lookupProgram);
    }
}
