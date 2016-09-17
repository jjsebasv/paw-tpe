package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAll();

    List<Course> findByName(String name);

    Course findById(int courseid);

}
