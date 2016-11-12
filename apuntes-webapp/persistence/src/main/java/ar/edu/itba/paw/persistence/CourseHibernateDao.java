package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.CourseProgramRelation;
import ar.edu.itba.paw.models.Program;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CourseHibernateDao extends AbstractCRUDHibernateDao<Course> implements CourseDao {

    public CourseHibernateDao() {
        super(Course.class);
    }

    @Override
    public List<Course> findByName(final String name) {
        final TypedQuery<Course> query = em.createQuery("FROM Course AS c WHERE LOWER(c.name) LIKE LOWER(:name)", Course.class);
        query.setParameter("name", "%" + name + "%");
        final List<Course> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<Course> findByTerm(String term) {
        final TypedQuery<Course> query = em.createQuery("FROM Course AS c WHERE LOWER(c.name) LIKE LOWER(:term) or c.code LIKE :term", Course.class);
        query.setParameter("term", "%" + term + "%");
        final List<Course> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public Course findByCode(final String code) {
        final TypedQuery<Course> query = em.createQuery("FROM Course AS c WHERE c.code = :code", Course.class);
        query.setParameter("code", code);
        final List<Course> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<CourseProgramRelation> findByProgramId(final long pk) {
        final TypedQuery<CourseProgramRelation> query = em.createQuery("SELECT r FROM CourseProgramRelation AS r " +
                "WHERE r.program.programid=:programid", CourseProgramRelation.class);
        query.setParameter("programid", pk);
        final List<CourseProgramRelation> list = query.getResultList();

        return list.isEmpty() ? null : list;
    }
}
