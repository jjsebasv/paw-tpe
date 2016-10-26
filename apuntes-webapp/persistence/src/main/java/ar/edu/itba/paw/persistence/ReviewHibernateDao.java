package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReviewHibernateDao implements ReviewDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Review createReview(Document file, Client user, double ranking, String review) {
        final Review rev = new Review(file, user, ranking, review);

        em.persist(rev);

        return rev;
    }

    @Override
    public List<Review> findByFileId(final int fileid) {
        final TypedQuery<Review> query = em.createQuery("FROM Review as r WHERE r.file.documentId = :fileid", Review.class);
        query.setParameter("fileid", fileid);
        return query.getResultList();
    }

    @Override
    public double getAverage(int fileid) {
        return 2;
    }

    @Override
    public List<Review> findByUser(final int userid) {
        final TypedQuery<Review> query = em.createQuery("FROM Review as r WHERE r.user.clientId = :userid", Review.class);
        query.setParameter("userid", userid);
        return query.getResultList();
    }
}
