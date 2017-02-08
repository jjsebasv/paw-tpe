package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Review;
import org.hibernate.validator.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReviewDTO {

    private long reviewid;

    private long fileid;

    private long userid;

    private int ranking;

    @NotEmpty
    private String review;

    public ReviewDTO(final Review review) {
        this.reviewid = review.getReviewid();
        this.fileid = review.getFile().getDocumentId();
        this.userid = review.getUser().getClientId();
        this.ranking = review.getRanking();
        this.review = review.getReview();
    }

    public ReviewDTO() {
    }

    public long getReviewid() {
        return reviewid;
    }

    public void setReviewid(long reviewid) {
        this.reviewid = reviewid;
    }

    public long getFileid() {
        return fileid;
    }

    public void setFileid(long fileid) {
        this.fileid = fileid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
