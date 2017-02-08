package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Course;
import org.hibernate.validator.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CourseDTO {

    private long courseid;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    public CourseDTO() {
    }

    public CourseDTO(final Course course) {
        this.courseid = course.getCourseid();
        this.code = course.getCode();
        this.name = course.getName();
    }

    public long getCourseid() {
        return courseid;
    }

    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

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
}
