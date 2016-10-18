package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;
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
public class ProgramRelatedCourseJdbcDaoTest {

    private static final String COURSE_CODE = "93.71";
    private static final String COURSE_NAME = "Curso de Ejemplo";

    private static final String PROGRAM_NAME = "Carrera de ejemplo";
    private static final String PROGRAM_SHORTNAME = "ejemplo";
    private static final char PROGRAM_GROUP = 'g';
    private static int COURSETOPROGRAM_SEMESTER = 3;

    @Autowired
    private DataSource ds;

    @Autowired
    private CourseJdbcDao courseDao;

    @Autowired
    private ProgramJdbcDao programDao;

    @Before
    public void setUp() throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        JdbcTestUtils.deleteFromTables(jdbcTemplate, CourseJdbcDao.COURSE_TABLE_NAME);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, CourseJdbcDao.COURSETOPROGRAM_TABE_NAME);
        JdbcTestUtils.deleteFromTables(jdbcTemplate, ProgramJdbcDao.PROGRAM_TABLE_NAME);
    }

    @Test
    public void testAddProgramToCourseRelationship() {
        final Course course = courseDao.create(COURSE_CODE, COURSE_NAME);
        final Program program = programDao.create(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP);

        courseDao.addProgramRelationship(course, program, COURSETOPROGRAM_SEMESTER);

        List<Course> courseList = courseDao.findByProgram(program.getProgramid());

        Assert.assertNotNull(courseList);
        Assert.assertFalse(courseList.isEmpty());
        Assert.assertEquals(1, courseList.size());

        Course relatedCourse = courseList.get(0);

        Assert.assertEquals(course, relatedCourse);

        Assert.assertTrue(courseDao.isRelatedTo(course, program));
    }


}
