package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.CourseProgramRelation;
import ar.edu.itba.paw.models.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public Map<Integer, List<Course>> findByProgram(final int programid) {

        final List<CourseProgramRelation> courses = courseDao.findByProgram(programid);

        Map<Integer, List<Course>> coursesMap = new HashMap<>(11);

        for (int i = 0; i < 11; i++) {
            coursesMap.put(i, new LinkedList<>());
        }

        for (CourseProgramRelation programRelation : courses) {
            coursesMap.get(programRelation.getSemester()).add(programRelation.getCourse());
        }

        return coursesMap;
    }

    @Override
    public Course create(final String code, final String name) {
        return courseDao.create(code, name);
    }

    @Override
    public void addProgramRelationship(final Course course, final Program program, final int semester) {
        if (!isRelatedTo(course, program)) {
            courseDao.addProgramRelationship(course, program, semester);
        }
    }

    @Override
    public boolean isRelatedTo(final Course course, final Program program) {
        return courseDao.isRelatedTo(course, program);
    }

	@Override
	public List<Program> getPrograms(int courseid) {
		// TODO Auto-generated method stub
		return courseDao.getPrograms(courseid);
	}
}
