package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Program;

import java.util.List;

public interface ProgramService extends GenericCRUDService<Program> {

    List<Program> findByName(String name);

    List<Program> getProgramsFromCourseId(long pk);
}
