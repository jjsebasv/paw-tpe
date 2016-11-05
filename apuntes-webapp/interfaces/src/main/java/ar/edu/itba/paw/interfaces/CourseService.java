package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;

import java.util.List;
import java.util.Map;

public interface CourseService extends GenericCRUDService<Course> {

    List<Course> getAll();

    List<Course> findByName(String name);

    List<Course> findByTerm(String term);

    Course findByCode(String code);

    Map<Integer, List<Course>> findByProgramId(long pk);

    void addProgramRelationship(Course course, Program program, int semester);

    boolean isRelatedTo(Course course, Program program);
}
