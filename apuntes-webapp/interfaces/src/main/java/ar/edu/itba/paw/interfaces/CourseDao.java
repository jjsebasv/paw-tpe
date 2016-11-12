package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.CourseProgramRelation;

import java.util.List;

public interface CourseDao extends GenericCRUDDao<Course> {

    List<Course> findByName(String name);

    List<Course> findByTerm(String term);

    Course findByCode(String code);

    List<CourseProgramRelation> findByProgramId(long pk);

}
