package ar.edu.itba.paw.models;

public class Course {

    private final int courseid;

    private final String code;

    private final String name;

    public Course(final int courseid, final String code, final String name) {
        this.courseid = courseid;
        this.code = code;
        this.name = name;
    }

    public int getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
