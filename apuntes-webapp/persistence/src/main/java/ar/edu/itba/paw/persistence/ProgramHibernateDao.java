package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ProgramDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProgramHibernateDao implements ProgramDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Program> getAll() {
        final TypedQuery<Program> query = em.createQuery("from Program", Program.class);
        final List<Program> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public Program findById(final int programid) {
        return em.find(Program.class, programid);
    }

    @Override
    public List<Program> findByName(final String name) {
        final TypedQuery<Program> query = em.createQuery("from Program as p where p.name = :name", Program.class);
        query.setParameter("name", name);
        final List<Program> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public Program create(final String name, final String shortName, final char group) {
        final Program program = new Program(name, shortName, group);

        em.persist(program);

        return program;
    }
}
