package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.CourseProgramRelation;
import ar.edu.itba.paw.models.Program;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CourseHibernateDao implements CourseDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Course> getAll() {
        final TypedQuery<Course> query = em.createQuery("from Course", Course.class);
        final List<Course> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<Course> findByName(final String name) {
        final TypedQuery<Course> query = em.createQuery("from Course as c where c.name like :name", Course.class);
        query.setParameter("name", "%" + name + "%");
        final List<Course> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public List<Course> findByTerm(String term) {
        final TypedQuery<Course> query = em.createQuery("from Course as c where LOWER(c.name) like lower(:term) or c.code like :term", Course.class);
        query.setParameter("term", "%" + term + "%");
        final List<Course> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public Course findById(int courseid) {
        return em.find(Course.class, courseid);
    }

    @Override
    public Course findByCode(final String code) {
        final TypedQuery<Course> query = em.createQuery("from Course as c where c.code = :code", Course.class);
        query.setParameter("code", code);
        final List<Course> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<CourseProgramRelation> findByProgram(int programid) {
        final TypedQuery<CourseProgramRelation> query = em.createQuery("SELECT r FROM CourseProgramRelation as r " +
                "WHERE r.program.programid=:programid", CourseProgramRelation.class);
        query.setParameter("programid", programid);
        final List<CourseProgramRelation> list = query.getResultList();

        return list.isEmpty() ? null : list;
    }

    @Override
    public Course create(final String code, final String name) {
        final Course course = new Course(code, name);

        em.persist(course);

        return course;
    }

    @Override
    public void addProgramRelationship(Course course, Program program, int semester) {

        final CourseProgramRelation courseProgramRelation = new CourseProgramRelation(program, course, semester);

        em.persist(courseProgramRelation);
    }

    @Override
    public boolean isRelatedTo(Course course, Program program) {

        final TypedQuery<CourseProgramRelation> query = em.createQuery("SELECT r FROM CourseProgramRelation as r " +
                "WHERE r.program.programid=:programid and r.course.courseid=:courseid", CourseProgramRelation.class);
        query.setParameter("programid", program.getProgramid());
        query.setParameter("courseid", course.getCourseid());
        final List<CourseProgramRelation> list = query.getResultList();

        return !list.isEmpty();
    }

    @Override
    public void delete(Course course) {
        em.remove(course);
    }
}
