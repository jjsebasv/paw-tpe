package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_review_id_seq")
    @SequenceGenerator(sequenceName = "reviews_review_id_seq", name = "reviews_review_id_seq", allocationSize = 1)
    @Column(name = "review_id")
    private int reviewid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Document file;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Client user;

    @Column
    private double ranking;

    @Column(length = 500, nullable = false)
    private String review;

    @Column(name = "date_uploaded", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUploaded;

    /* package */ Review() {
        // Just for Hibernate, we love you!
    }

    public Review(final Document file, final Client user, final double ranking, final String review) {
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

    public Date getDateUploaded() {
        return dateUploaded;
    }
}
