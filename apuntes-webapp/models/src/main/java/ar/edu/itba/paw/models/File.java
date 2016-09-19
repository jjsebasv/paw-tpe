package ar.edu.itba.paw.models;

import java.io.InputStream;

public class File {
    private final int fileid;
    private final User user;
    private final Course course;
    private final String subject;
    private final String fileName;
    private final int fileSize;
    private final InputStream data;

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

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public String getSubject() {
        return subject;
    }

    public String getFileName() {
        return fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public InputStream getData() {
        return data;
    }
}
