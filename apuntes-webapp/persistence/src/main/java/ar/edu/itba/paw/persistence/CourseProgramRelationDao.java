package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.interfaces.ProgramDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.CourseProgramRelation;
import ar.edu.itba.paw.models.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CourseProgramRelationDao extends AbstractCRUDHibernateDao<CourseProgramRelation> implements ar.edu.itba.paw.interfaces.CourseProgramRelationDao {

    private final ProgramDao ps;
    private final CourseDao cs;

    @Autowired
    CourseProgramRelationDao(ProgramDao ps, CourseDao cs) {
        super(CourseProgramRelation.class);
        this.ps = ps;
        this.cs = cs;
    }

    @Override
    public CourseProgramRelation findById(long pk) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(long pk) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CourseProgramRelation findById(long programPk, long coursePk) {

        final TypedQuery<CourseProgramRelation> query = em.createQuery("SELECT r FROM CourseProgramRelation AS r " +
                "WHERE r.program.programid=:programid and r.course.courseid=:courseid", CourseProgramRelation.class);
        query.setParameter("programid", programPk);
        query.setParameter("courseid", coursePk);
        final List<CourseProgramRelation> list = query.getResultList();

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void delete(long programPk, long coursePk) {
        final CourseProgramRelation modelInstance = this.findById(programPk, coursePk);

        if (modelInstance != null) {
            em.remove(modelInstance);
        }
    }

    @Override
    public CourseProgramRelation addProgramRelationship(final long coursePk, final long programPk, int semester) {

        final Program program = ps.findById(programPk);
        final Course course = cs.findById(coursePk);

        final CourseProgramRelation courseProgramRelation = new CourseProgramRelation(program, course, semester);

        em.persist(courseProgramRelation);

        return courseProgramRelation;
    }

    @Override
    public boolean isRelatedTo(final long coursePk, final long programPk) {

        final TypedQuery<CourseProgramRelation> query = em.createQuery("SELECT r FROM CourseProgramRelation AS r " +
                "WHERE r.program.programid=:programid and r.course.courseid=:courseid", CourseProgramRelation.class);
        query.setParameter("programid", programPk);
        query.setParameter("courseid", coursePk);
        final List<CourseProgramRelation> list = query.getResultList();

        return !list.isEmpty();
    }
}
