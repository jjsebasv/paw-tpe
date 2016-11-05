package ar.edu.itba.paw.builders;

import ar.edu.itba.paw.interfaces.ModelBuilder;
import ar.edu.itba.paw.models.Course;

public class CourseBuilder implements ModelBuilder<Course> {
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