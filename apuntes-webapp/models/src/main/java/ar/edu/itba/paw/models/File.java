package ar.edu.itba.paw.models;

import java.io.InputStream;

public class File {
    private int fileid;
    private User user;
    private Course course;
    private String subject;
    private String fileName;
    private int fileSize;
    private InputStream data;

    public File(final int fileid, final User user, final Course course, final String subject, final String fileName, final int fileSize, final InputStream data) {
        this.fileid = fileid;
        this.user = user;
        this.course = course;
        this.subject = subject;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.data = data;
    }

    public int getFileid() {
        return fileid;
    }

    public void setFileid(int fileid) {
        this.fileid = fileid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int filesize) {
        this.fileSize = filesize;
    }
}
