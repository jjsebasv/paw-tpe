package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends AbstractCRUDService<Review> implements ReviewService {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        super(reviewDao);
        this.reviewDao = reviewDao;
    }

    @Override
    public void update(final long pk, final Review from) {
        final Review instance = findById(pk);

        instance.setRanking(from.getRanking());
        instance.setReview(from.getReview());
    }

    @Override
    public List<Review> findByFileId(final long pk) {
        return reviewDao.findByFileId(pk);
    }

    @Override
    public double getAverageFromFileId(final long pk) {
        return reviewDao.getAverageFromFileId(pk);
    }

    @Override
    public List<Review> findByUserId(final long pk) {
        return reviewDao.findByUserId(pk);
    }

}
