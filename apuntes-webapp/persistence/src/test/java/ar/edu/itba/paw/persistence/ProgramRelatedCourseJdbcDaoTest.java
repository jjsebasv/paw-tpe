package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.CourseProgramRelation;
import ar.edu.itba.paw.models.Program;
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
public class ProgramRelatedCourseJdbcDaoTest {

    private static final String COURSE_CODE = "93.71";
    private static final String COURSE_NAME = "Curso de Ejemplo";

    private static final String PROGRAM_NAME = "Carrera de ejemplo";
    private static final String PROGRAM_SHORTNAME = "ejemplo";
    private static final char PROGRAM_GROUP = 'g';
    private static int COURSETOPROGRAM_SEMESTER = 3;

    @Autowired
    private CourseHibernateDao courseDao;

    @Autowired
    private CourseProgramRelationDao courseProgramRelationDao;

    @Autowired
    private ProgramHibernateDao programDao;

    @Test
    public void testAddProgramToCourseRelationship() {
        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP));

        courseProgramRelationDao.addProgramRelationship(course.getCourseid(), program.getProgramid(), COURSETOPROGRAM_SEMESTER);

        List<CourseProgramRelation> courseList = courseDao.findByProgramId(program.getProgramid());

        Assert.assertNotNull(courseList);
        Assert.assertFalse(courseList.isEmpty());
        Assert.assertEquals(1, courseList.size());

        Course relatedCourse = courseList.get(0).getCourse();

        Assert.assertEquals(course, relatedCourse);

        Assert.assertTrue(courseProgramRelationDao.isRelatedTo(course.getCourseid(), program.getProgramid()));
    }

    @Test
    public void testFindProgramsFromCourseId() {

        final Course course = courseDao.create(new Course(COURSE_CODE, COURSE_NAME));
        final Program program = programDao.create(new Program(PROGRAM_NAME, PROGRAM_SHORTNAME, PROGRAM_GROUP));

        courseProgramRelationDao.addProgramRelationship(course.getCourseid(), program.getProgramid(), COURSETOPROGRAM_SEMESTER);

        List<Program> programList = programDao.getProgramsFromCourseId(course.getCourseid());

        Assert.assertNotNull(programList);
        Assert.assertFalse(programList.isEmpty());
        Assert.assertEquals(1, programList.size());

        Program relatedProgram = programList.get(0);

        Assert.assertEquals(program, relatedProgram);

        Assert.assertTrue(courseProgramRelationDao.isRelatedTo(course.getCourseid(), program.getProgramid()));
    }
}
