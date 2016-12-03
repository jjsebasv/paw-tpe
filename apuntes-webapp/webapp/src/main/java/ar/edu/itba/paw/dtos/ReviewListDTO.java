package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Review;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class ReviewListDTO {

    private List<ReviewDTO> reviewList;

    public ReviewListDTO() {
    }

    public ReviewListDTO(final List<Review> reviewList) {
        this.reviewList = reviewList.stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewDTO> reviewList) {
        this.reviewList = reviewList;
    }
}
