package ar.edu.itba.paw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
	
	@Override
	public Review createReview(int reviewid, int fileid, int userid, double ranking, String review) {
		// TODO Auto-generated method stub
		return reviewDao.createReview(reviewid, fileid, userid, ranking, review);
	}

}
