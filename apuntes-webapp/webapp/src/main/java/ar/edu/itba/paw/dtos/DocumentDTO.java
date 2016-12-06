package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Document;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Base64;
import java.util.Date;

@XmlRootElement
public class DocumentDTO {

    private long documentId;

    private long userid;

    private long courseid;

    @NotEmpty
    private String subject;

    @NotEmpty
    private String documentName;

    private String data;

    private Date dateUploaded;

    @NotEmpty
    private String description;

    public DocumentDTO() {
    }

    public DocumentDTO(final Document document) {
        this.documentId = document.getDocumentId();
        this.userid = document.getUser().getClientId();
        this.courseid = document.getCourse().getCourseid();
        this.subject = document.getSubject();
        this.documentName = document.getDocumentName();
        this.data = Base64.getEncoder().encodeToString(document.getData());
        this.dateUploaded = document.getDateUploaded();
        this.description = document.getDescription();
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public long getDocumentSize() {
        if (this.data == null) {
            return 0;
        }

        return data.length();
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
