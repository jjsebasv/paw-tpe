package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DocumentDao;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DocumentHibernateDao implements DocumentDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Document findById(int documentid) {
        return em.find(Document.class, documentid);
    }

    @Override
    public List<Document> findByCourseId(int id) {
        final TypedQuery<Document> query = em.createQuery("FROM Document as d WHERE d.course.courseid = :courseid", Document.class);
        query.setParameter("courseid", id);
        return query.getResultList();
    }

    @Override
    public List<Document> findByClient(Client uploader) {

        final TypedQuery<Document> query = em.createQuery("FROM Document as d WHERE d.user.clientId = :clientid", Document.class);
        query.setParameter("clientid", uploader.getClientId());
        return query.getResultList();
    }

    @Override
    public List<Document> getAll() {
        final TypedQuery<Document> query = em.createQuery("from Document", Document.class);
        return query.getResultList();
    }

    @Override
    public Document createDocument(Client user, Course course, String subject, String filename, int filesize, byte[] data) {

        final Document document = new Document(user, course, subject, filename, filesize, data);

        em.persist(document);

        return document;
    }
}
