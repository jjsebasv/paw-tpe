package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.CourseProgramRelation;

public interface CourseProgramRelationDao extends GenericCRUDDao<CourseProgramRelation> {

    CourseProgramRelation findById(long programPk, long coursePk);

    void delete(long programPk, long coursePk);

    CourseProgramRelation addProgramRelationship(long coursePk, long programPk, int semester);

    boolean isRelatedTo(long coursePk, long programPk);
}
