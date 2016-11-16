package ar.edu.itba.paw.models.builders;

import ar.edu.itba.paw.models.Course;

public class CourseBuilder extends ModelBuilder<Course> {
    private String code;
    private String name;

    public CourseBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Course createModel() {
        return new Course(code, name);
    }
}