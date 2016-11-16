package ar.edu.itba.paw.forms.admin;

import ar.edu.itba.paw.forms.ReviewForm;
import ar.edu.itba.paw.interfaces.admin.IAdminForm;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.builders.ReviewBuilder;

public class ReviewAdminForm extends ReviewForm implements IAdminForm<Review> {
    @Override
    public Review buildObjectFromForm() {
        return new ReviewBuilder().setRanking(getRanking()).setReview(getReview()).createModel();
    }

    @Override
    public void loadValuesFromInstance(Review instance) {
        setReview(instance.getReview());
        setRanking(instance.getRanking());
    }
}
