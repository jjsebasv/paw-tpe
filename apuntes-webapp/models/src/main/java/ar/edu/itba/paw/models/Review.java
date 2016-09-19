package ar.edu.itba.paw.models;

public class Review {

    private final int reviewid;
    private final File file;
    private final User user;
    private final double ranking;
    private final String review;

    public Review(final int reviewid, final File file, final User user, final double ranking, final String review) {

        this.reviewid = reviewid;
        this.file = file;
        this.user = user;
        this.ranking = ranking;
        this.review = review;
    }

    public int getReviewid() {
        return reviewid;
    }

    public File getFile() {
        return file;
    }

    public User getUser() {
        return user;
    }

    public double getRanking() {
        return ranking;
    }

    public String getReview() {
        return review;
    }
}
