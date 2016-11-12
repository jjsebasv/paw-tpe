package ar.edu.itba.paw.forms.admin;


import ar.edu.itba.paw.models.builders.CourseBuilder;
import ar.edu.itba.paw.interfaces.admin.IAdminForm;
import ar.edu.itba.paw.models.Course;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CourseAdminForm implements IAdminForm<Course> {

    @Size(min = 5, max = 5)
    @Pattern(regexp = "[\\d]{2}\\.[\\d]{2}")
    private String code;

    @Size(min = 3, max = 200)
    @Pattern(regexp = "[a-zA-Z0-9\\sáéíóú]+")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Course buildObjectFromForm() {
        return new CourseBuilder().setCode(code).setName(name).createModel();
    }

    @Override
    public void loadValuesFromInstance(Course instance) {
        code = instance.getCode();
        name = instance.getName();
    }
}
