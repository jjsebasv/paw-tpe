package ar.edu.itba.paw.models;

public class Course {

    private final int courseid;

    private final String name;

    public Course(int courseid, String name) {
        this.courseid = courseid;
        this.name = name;
    }

    public int getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }
}
