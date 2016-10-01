package ar.edu.itba.paw.models;

public class Review {

    private final int reviewid;
    private  Document file;
    private  Client user;
    private final double ranking;
    private final String review;

    public Review(final int reviewid, final Document file, final Client user, final double ranking, final String review) {

        this.reviewid = reviewid;
        this.file = file;
        this.user = user;
        this.ranking = ranking;
        this.review = review;
    }

    public int getReviewid() {
        return reviewid;
    }

    public Document getFile() {
        return file;
    }

    public Client getUser() {
        return user;
    }

    public double getRanking() {
        return ranking;
    }

    public String getReview() {
        return review;
    }
}
