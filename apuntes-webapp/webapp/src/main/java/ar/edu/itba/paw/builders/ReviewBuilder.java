package ar.edu.itba.paw.builders;

import ar.edu.itba.paw.interfaces.ModelBuilder;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;

public class ReviewBuilder implements ModelBuilder<Review> {
    private Document file;
    private Client user;
    private double ranking;
    private String review;

    public ReviewBuilder setFile(Document file) {
        this.file = file;
        return this;
    }

    public ReviewBuilder setUser(Client user) {
        this.user = user;
        return this;
    }

    public ReviewBuilder setRanking(double ranking) {
        this.ranking = ranking;
        return this;
    }

    public ReviewBuilder setReview(String review) {
        this.review = review;
        return this;
    }

    public Review createModel() {
        return new Review(file, user, ranking, review);
    }
}