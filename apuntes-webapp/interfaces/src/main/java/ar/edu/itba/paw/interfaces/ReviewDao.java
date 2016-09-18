package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Review;

public interface ReviewDao {

	Review createReview(int reviewid, int fileid, int userid, double ranking, String review);

}
