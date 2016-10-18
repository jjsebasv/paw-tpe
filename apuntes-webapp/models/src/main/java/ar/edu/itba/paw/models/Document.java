package ar.edu.itba.paw.models;

import java.io.InputStream;

public class Document {

    private final int documentId;
    private final Client user;
    private final Course course;
    private final String subject;
    private final String documentName;
    private final int documentSize;
    private final InputStream data;

    public Document(final int documentId, final Client user, final Course course, final String subject, final String documentName, final int documentSize, final InputStream data) {
        this.documentId = documentId;
        this.user = user;
        this.course = course;
        this.subject = subject;
        this.documentName = documentName;
        this.documentSize = documentSize;
        this.data = data;
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

    public InputStream getData() {
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
