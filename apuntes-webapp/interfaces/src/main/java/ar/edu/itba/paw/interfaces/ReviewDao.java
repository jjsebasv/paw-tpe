package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Review;

import java.util.List;

public interface ReviewDao extends GenericCRUDDao<Review> {

    List<Review> findByFileId(long pk);

    double getAverageFromFileId(long pk);

    List<Review> findByUserId(long pk);
}
