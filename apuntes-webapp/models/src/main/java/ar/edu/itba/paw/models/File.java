package ar.edu.itba.paw.models;

import java.io.InputStream;

public class File {
    private int fileid;
    private int userid;
    private int courseid;
    private String subject;
    private String fileName;
    private int fileSize;
    private InputStream data;

    public File(final int fileid, final int userid, final int courseid, final String subject, final String fileName, final int fileSize, final InputStream data) {
        this.fileid = fileid;
        this.userid = userid;
        this.courseid = courseid;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
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
