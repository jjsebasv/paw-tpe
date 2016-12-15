package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Course;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class ExpandedCourseListDTO {
    private List<ExpandedCourseDTO> courseList;

    public ExpandedCourseListDTO() {
    }

    public ExpandedCourseListDTO(final List<ExpandedCourseDTO> courseList) {
        this.courseList = courseList;
    }

    public List<ExpandedCourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<ExpandedCourseDTO> courseList) {
        this.courseList = courseList;
    }
}
