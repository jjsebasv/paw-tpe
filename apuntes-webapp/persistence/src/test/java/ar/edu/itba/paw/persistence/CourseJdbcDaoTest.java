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

//    @Test
//    public void testFindByIdNonExistingUser() {
//
//        final Client user = clientDao.findById(13);
//
//        // Postcondiciones
//        Assert.assertNull("User with id 42 is null!", user);
//    }
}
