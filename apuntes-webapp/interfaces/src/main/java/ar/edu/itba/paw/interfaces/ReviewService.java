package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.User;

public interface ReviewService {

    Review createReview(File file, User user, double ranking, String review);

    List<Review> findByFileId(int fileid);
}
