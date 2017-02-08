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
public class ProgramServiceImpl extends AbstractCRUDService<Program> implements ProgramService {

    private final ProgramDao programDao;

    @Autowired
    public ProgramServiceImpl(ProgramDao programDao) {
        super(programDao);
        this.programDao = programDao;
    }

    @Override
    public List<Program> findByName(final String name) {
        return programDao.findByName(name);
    }

    @Override
    public List<Program> getProgramsFromCourseId(final long pk) {
        return programDao.getProgramsFromCourseId(pk);
    }

    @Override
    public List<Program> getByUniversityId(long pk) {
        return programDao.getByUniversityId(pk);
    }

    @Override
    public void update(final long pk, Program from) {

        final Program instance = findById(pk);

        instance.setName(from.getName());
        instance.setShortName(from.getShortName());
        instance.setGroup(from.getGroup());
        instance.setUniversity(from.getUniversity());
    }
}
