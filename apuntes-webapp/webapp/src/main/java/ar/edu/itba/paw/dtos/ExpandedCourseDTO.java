package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Course;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExpandedCourseDTO extends CourseDTO {

    private int semester;

    public ExpandedCourseDTO() {
    }

    public ExpandedCourseDTO(final Course course) {
        super(course);
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

}
