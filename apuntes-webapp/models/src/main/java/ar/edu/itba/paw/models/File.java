package ar.edu.itba.paw.models;

public class File {
    private int fileid;
    private int userid;
    private int courseid;
    private String subject;
    private String filename;
    private byte[] data;

    public File(int fileid, int userid, int courseid, String subject, String filename, byte[] data) {
        this.fileid = fileid;
        this.userid = userid;
        this.courseid = courseid;
        this.subject = subject;
        this.filename = filename;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
