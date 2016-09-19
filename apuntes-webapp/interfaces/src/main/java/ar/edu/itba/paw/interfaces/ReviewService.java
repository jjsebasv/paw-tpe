package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Review;

public interface ReviewService {

	public Review createReview(int fileid, long userid, double ranking, String review);

	public List<Review> findByFileId(int fileid);
	
	public String getUsername(final int userid);
	
}
