package ar.edu.itba.paw.services;

import java.util.List;

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
	public Review createReview(int fileid, int userid, double ranking, String review) {
		// TODO Auto-generated method stub
		return reviewDao.createReview(fileid, userid, ranking, review);
	}

	@Override
	public List<Review> findByFileId(int fileid) {
		// TODO Auto-generated method stub
		return reviewDao.findByFileId(fileid);
	}

	@Override
	public String getUsername(int userid) {
		// TODO Auto-generated method stub
		return reviewDao.getUsername(userid);
	}

}
