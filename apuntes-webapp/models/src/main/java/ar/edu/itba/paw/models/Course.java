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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseid != course.courseid) return false;
        return code != null ? code.equals(course.code) : course.code == null;

    }

    @Override
    public int hashCode() {
        int result = courseid;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, name);
    }
}
