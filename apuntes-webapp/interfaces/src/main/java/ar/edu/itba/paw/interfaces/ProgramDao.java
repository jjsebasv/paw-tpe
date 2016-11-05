package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Program;

import java.util.List;

public interface ProgramDao extends GenericCRUDDao<Program> {

    List<Program> findByName(String name);

    List<Program> getProgramsFromCourseId(long pk);
}
