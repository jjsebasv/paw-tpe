package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.Client;

public interface ReviewDao {

    Review createReview(Document file, Client user, double ranking, String review);

    List<Review> findByFileId(int fileid);

	double getAverage(int fileid);

	List<Review> findByUser(int userid);
}
