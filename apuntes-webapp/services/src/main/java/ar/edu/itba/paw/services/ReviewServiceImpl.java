package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public Review createReview(final File file, final User user, double ranking, String review) {
        return reviewDao.createReview(file, user, ranking, review);
    }

    @Override
    public List<Review> findByFileId(int fileid) {
        return reviewDao.findByFileId(fileid);
    }

	@Override
	public double getAverage(int fileid) {
		// TODO Auto-generated method stub
		return reviewDao.getAverage(fileid);
	}

}
