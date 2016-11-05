package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.models.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReviewHibernateDao extends AbstractCRUDHibernateDao<Review> implements ReviewDao {

    ReviewHibernateDao() {
        super(Review.class);
    }

    @Override
    public List<Review> findByFileId(final long pk) {
        final TypedQuery<Review> query = em.createQuery("FROM Review as r WHERE r.file.documentId = :fileid", Review.class);
        query.setParameter("fileid", pk);
        return query.getResultList();
    }

    @Override
    public double getAverageFromFileId(final long pk) {
        final TypedQuery<Double> query = em.createQuery("select ROUND(coalesce(AVG(r.ranking), 0),2) FROM Review as r WHERE r.file.documentId = :fileid", Double.class);
        query.setParameter("fileid", pk);
        return query.getSingleResult();
    }

    @Override
    public List<Review> findByUserId(final long pk) {
        final TypedQuery<Review> query = em.createQuery("FROM Review as r WHERE r.user.clientId = :userid", Review.class);
        query.setParameter("userid", pk);
        return query.getResultList();
    }
}
