package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.CourseProgramRelationDao;
import ar.edu.itba.paw.interfaces.CourseProgramRelationService;
import ar.edu.itba.paw.models.CourseProgramRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseProgramRelationServiceImpl extends AbstractCRUDService<CourseProgramRelation> implements CourseProgramRelationService {

    private final CourseProgramRelationDao relationDao;

    @Autowired
    public CourseProgramRelationServiceImpl(final CourseProgramRelationDao relationDao) {
        super(relationDao);
        this.relationDao = relationDao;
    }

    @Override
    public void update(long pk, CourseProgramRelation from) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CourseProgramRelation findById(final long programPk, final long coursePk) {
        return relationDao.findById(programPk, coursePk);
    }

    @Override
    public void delete(final long programPk, final long coursePk) {
        relationDao.delete(programPk, coursePk);
    }

    @Override
    public CourseProgramRelation addProgramRelationship(final long coursePk, final long programPk, final int semester) {
        if (!isRelatedTo(coursePk, programPk)) {
            return relationDao.addProgramRelationship(coursePk, programPk, semester);
        }
        return null;
    }

    @Override
    public void update(long coursePk, long programPk, int semester) {
        final CourseProgramRelation relation = findById(programPk, coursePk);

        relation.setSemester(semester);
    }

    @Override
    public boolean isRelatedTo(final long coursePk, final long programPk) {
        return relationDao.isRelatedTo(coursePk, programPk);
    }

}