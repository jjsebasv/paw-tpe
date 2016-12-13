package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ProgramDao;
import ar.edu.itba.paw.models.Program;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProgramHibernateDao extends AbstractCRUDHibernateDao<Program> implements ProgramDao {

    ProgramHibernateDao() {
        super(Program.class);
    }

    @Override
    public List<Program> findByName(final String name) {
        final TypedQuery<Program> query = em.createQuery("from Program as p where p.name = :name", Program.class);
        query.setParameter("name", name);
        final List<Program> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<Program> getProgramsFromCourseId(final long pk) {
        final TypedQuery<Program> query = em.createQuery("SELECT r.program FROM CourseProgramRelation as r " +
                "WHERE r.course.courseid=:courseid", Program.class);
        query.setParameter("courseid", pk);
        final List<Program> list = query.getResultList();

        return list.isEmpty() ? null : list;
    }

    @Override
    public List<Program> getByUniversityId(long pk) {
        final TypedQuery<Program> query = em.createQuery("from Program as p where p.university.universityId = :uid", Program.class);
        query.setParameter("uid", pk);
        final List<Program> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

}
