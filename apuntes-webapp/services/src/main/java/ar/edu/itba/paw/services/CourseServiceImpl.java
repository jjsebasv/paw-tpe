package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(final CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public List<Course> findByName(final String name) {
        return courseDao.findByName(name);
    }

    @Override
    public Course findById(final int courseid) {
        return courseDao.findById(courseid);
    }

    @Override
    public Course findByCode(final String code) {
        return courseDao.findByCode(code);
    }

    @Override
    public List<Course> findByProgram(final int programid) {
        return courseDao.findByProgram(programid);
    }

    @Override
    public Course create(final String code, final String name) {
        return courseDao.create(code, name);
    }

    @Override
    public void addProgramRelationship(Course course, Program program) {
        if (!isRelatedTo(course, program)) {
            courseDao.addProgramRelationship(course, program);
        }
    }

    @Override
    public boolean isRelatedTo(final Course course, final Program program) {
        return courseDao.isRelatedTo(course, program);
    }
}
