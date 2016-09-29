package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ReviewDao;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.Client;
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
    public Review createReview(final Document file, final Client user, double ranking, String review) {
        return reviewDao.createReview(file, user, ranking, review);
    }

    @Override
    public List<Review> findByFileId(int fileid) {
    	List<Review> list = reviewDao.findByFileId(fileid);
      return list;
    }

	@Override
	public double getAverage(int fileid) {
		// TODO Auto-generated method stub
		return reviewDao.getAverage(fileid);
	}

}
