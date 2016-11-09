package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;

import java.util.List;

public interface ReviewService extends GenericCRUDService<Review> {

    List<Review> findByFileId(long pk);

    double getAverageFromFileId(long pk);

    List<Review> findByUserId(long pk);

    boolean canReview(Document document, Client client);
}
