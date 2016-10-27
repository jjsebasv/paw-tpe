package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Program;

import java.util.List;

public interface ProgramDao {

    List<Program> getAll();

    Program findById(int programid);

    List<Program> findByName(String name);

    Program create(String name, String shortName, char group);

    List<Program> getPrograms(int courseid);
}
