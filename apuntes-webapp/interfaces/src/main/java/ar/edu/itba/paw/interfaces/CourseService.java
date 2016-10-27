package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;

import java.util.List;
import java.util.Map;

public interface CourseService {

    List<Course> getAll();

    List<Course> findByName(String name);

    Course findById(int courseid);

    Course findByCode(String code);

    Map<Integer, List<Course>> findByProgram(int programid);

    Course create(String code, String name);

    void addProgramRelationship(Course course, Program program, int semester);

    boolean isRelatedTo(Course course, Program program);
}
