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
    private Long reviewid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id")
    private Document file;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id")
    private Client user;

    @Column
    private double ranking;

    @Column(length = 500, nullable = false)
    private String review;

    @Column(name = "date_uploaded", updatable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        return reviewid.equals(review.reviewid);

    }

    @Override
    public int hashCode() {
        return reviewid.hashCode();
    }

    public long getReviewid() {
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

    @PrePersist
    protected void onCreate() {
        this.dateUploaded = new Date();
    }
}
