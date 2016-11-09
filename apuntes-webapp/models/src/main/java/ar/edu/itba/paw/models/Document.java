package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_document_id_seq")
    @SequenceGenerator(sequenceName = "document_document_id_seq", name = "document_document_id_seq", allocationSize = 1)
    @Column(name = "document_id")
    private Long documentId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id")
    private Client user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(length = 100, nullable = false)
    private String subject;

    @Column(name = "document_name", length = 300, nullable = false)
    private String documentName;

    @Column(name = "document_size")
    private long documentSize;

    @Column(name = "uploaded_document", nullable = false)
    private byte[] data;

    @Column(name = "date_uploaded", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUploaded;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id")
    private List<Review> reviews;

    public Document(final Client user, final Course course, final String subject, final String documentName, final long documentSize, final byte[] data) {
        this.user = user;
        this.course = course;
        this.subject = subject;
        this.documentName = documentName;
        this.documentSize = documentSize;
        this.data = data;
    }

    /* package */ Document() {
        // Just for Hibernate, we love you!
    }

    public long getDocumentId() {
        return documentId;
    }

    public Client getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public String getSubject() {
        return subject;
    }

    public String getDocumentName() {
        return documentName;
    }

    public long getDocumentSize() {
        return documentSize;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return documentId.equals(document.documentId);

    }

    @Override
    public int hashCode() {
        return documentId.hashCode();
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    @PrePersist
    protected void onCreate() {
        this.dateUploaded = new Date();
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public void setDocumentSize(long documentSize) {
        this.documentSize = documentSize;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
