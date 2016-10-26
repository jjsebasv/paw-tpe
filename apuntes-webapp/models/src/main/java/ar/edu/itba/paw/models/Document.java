package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_document_id_seq")
    @SequenceGenerator(sequenceName = "document_document_id_seq", name = "document_document_id_seq", allocationSize = 1)
    @Column(name = "document_id")
    private Integer documentId;

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
    private int documentSize;

    @Column(nullable = false)
    private byte[] data;


    public Document(final Client user, final Course course, final String subject, final String documentName, final int documentSize, final byte[] data) {
        this.user = user;
        this.course = course;
        this.subject = subject;
        this.documentName = documentName;
        this.documentSize = documentSize;
        this.data = data;
    }

    public Document() {
    }

    public int getDocumentId() {
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

    public int getDocumentSize() {
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

        return documentId == document.documentId;

    }

    @Override
    public int hashCode() {
        return documentId;
    }
}
