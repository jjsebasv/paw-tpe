package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ProgramDao;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramDao programDao;

    @Autowired
    public ProgramServiceImpl(ProgramDao programDao) {
        this.programDao = programDao;
    }

    @Override
    public List<Program> getAll() {
        return programDao.getAll();
    }

    @Override
    public Program findById(final int programid) {
        return programDao.findById(programid);
    }

    @Override
    public List<Program> findByName(final String name) {
        return programDao.findByName(name);
    }
}
