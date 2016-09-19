package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Review;

public interface ReviewDao {

	Review createReview(int fileid, int userid, double ranking, String review);

	List<Review> findByFileId(int fileid);

	String getUsername(int userid);

}
