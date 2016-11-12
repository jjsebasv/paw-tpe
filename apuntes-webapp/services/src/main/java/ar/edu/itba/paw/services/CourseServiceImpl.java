package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.CourseProgramRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CourseServiceImpl extends AbstractCRUDService<Course> implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(final CourseDao courseDao) {
        super(courseDao);
        this.courseDao = courseDao;
    }

    @Override
    public List<Course> findByName(final String name) {
        return courseDao.findByName(name);
    }

    @Override
    public List<Course> findByTerm(final String term) {
        return courseDao.findByTerm(term);
    }

    @Override
    public Course findByCode(final String code) {
        return courseDao.findByCode(code);
    }

    @Override
    public Map<Integer, List<Course>> findByProgramId(final long pk) {

        final List<CourseProgramRelation> courses = courseDao.findByProgramId(pk);

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
    public void update(final long pk, final Course from) {
        final Course instance = findById(pk);

        instance.setCode(from.getCode());
        instance.setName(from.getName());
    }
}
