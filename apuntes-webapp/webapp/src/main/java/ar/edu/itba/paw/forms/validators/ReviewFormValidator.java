package ar.edu.itba.paw.forms.validators;


import ar.edu.itba.paw.forms.ReviewForm;
import ar.edu.itba.paw.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReviewFormValidator implements Validator {

    private final ReviewService reviewService;

    @Autowired
    public ReviewFormValidator(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public boolean supports(Class clazz) {
        return ReviewForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {

        ReviewForm reviewForm = (ReviewForm) obj;

        final boolean userHasReviewed = reviewService.findByFileId(reviewForm.getDocumentId())
                .stream()
                .anyMatch(review -> review.getUser().getClientId() == reviewForm.getClientId());

        if (userHasReviewed) {
            errors.reject("models.review.exists");
        }

    }
}
