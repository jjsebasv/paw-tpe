package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ProgramDao;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    @Override
    public Program create(final String name, final String shortName, final char group) {
        return programDao.create(name, shortName, group);
    }

    @Override
    public List<Program> getPrograms(final int courseid) {
        return programDao.getPrograms(courseid);
    }

    @Override
    public void delete(final int programid) {
        programDao.delete(programid);
    }

    @Override
    public void update(final int programid, Program from) {

        final Program instance = findById(programid);

        instance.setName(from.getName());
        instance.setShortName(from.getShortName());
        instance.setGroup(from.getGroup());
    }


}
