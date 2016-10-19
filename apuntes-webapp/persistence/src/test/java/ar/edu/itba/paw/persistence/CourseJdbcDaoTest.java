package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Course;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class CourseJdbcDaoTest {

    private static final String COURSE_CODE = "93.71";
    private static final String COURSE_NAME = "Curso de Ejemplo";

    @Autowired
    private DataSource ds;

    @Autowired
    private CourseJdbcDao courseDao;

    @Before
    public void setUp() throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        JdbcTestUtils.deleteFromTables(jdbcTemplate, CourseJdbcDao.COURSETOPROGRAM_TABE_NAME);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, CourseJdbcDao.COURSE_TABLE_NAME);
    }

    @Test
    public void testCreate() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);

        Assert.assertNotNull(course);
        Assert.assertEquals(COURSE_CODE, course.getCode());
        Assert.assertEquals(COURSE_NAME, course.getName());
        Assert.assertTrue(course.getCourseid() >= 0);
    }

    @Test
    public void testFindByName() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);

        final List<Course> list = courseDao.findByName(COURSE_NAME);

        Assert.assertNotNull(list);

        Assert.assertFalse(list.isEmpty());

        final Course lookupCourse = list.get(0);

        Assert.assertEquals(course, lookupCourse);
    }

    @Test
    public void testFindByNonExistingName() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);

        final List<Course> list = courseDao.findByName("abc");

        Assert.assertNotNull(list);

        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void testFindByCode() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);

        final Course lookupCourse = courseDao.findByCode(COURSE_CODE);

        Assert.assertNotNull(lookupCourse);

        Assert.assertEquals(course, lookupCourse);
    }

    @Test
    public void testFindByNonExistingCode() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);

        final Course lookupCourse = courseDao.findByCode("00.00");

        Assert.assertNull(lookupCourse);
    }

    @Test
    public void testFindById() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final int id = course.getCourseid();

        final Course lookupCourse = courseDao.findById(id);

        Assert.assertNotNull(lookupCourse);

        Assert.assertEquals(course, lookupCourse);
    }

    @Test
    public void testFindByNonExistingId() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final int id = course.getCourseid();

        final Course lookupCourse = courseDao.findById(200);

        Assert.assertNull(lookupCourse);
    }

    @Test
    public void getAll() {

        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);

        final List<Course> list = courseDao.getAll();

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());

        final Course lookupCourse = list.get(0);

        Assert.assertEquals(course, lookupCourse);
    }
}
