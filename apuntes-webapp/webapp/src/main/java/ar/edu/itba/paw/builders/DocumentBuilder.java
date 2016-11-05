package ar.edu.itba.paw.builders;

import ar.edu.itba.paw.interfaces.ModelBuilder;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;

public class DocumentBuilder implements ModelBuilder<Document> {
    private Client user;
    private Course course;
    private String subject;
    private String documentName;
    private long documentSize;
    private byte[] data;

    public DocumentBuilder setUser(Client user) {
        this.user = user;
        return this;
    }

    public DocumentBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    public DocumentBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public DocumentBuilder setDocumentName(String documentName) {
        this.documentName = documentName;
        return this;
    }

    public DocumentBuilder setDocumentSize(long documentSize) {
        this.documentSize = documentSize;
        return this;
    }

    public DocumentBuilder setData(byte[] data) {
        this.data = data;
        return this;
    }

    public Document createModel() {
        return new Document(user, course, subject, documentName, documentSize, data);
    }
}