package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Course;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class CourseListDTO {
    private List<CourseDTO> courseList;

    public CourseListDTO() {
    }

    public CourseListDTO(final List<Course> courseList) {
        this.courseList = courseList.stream().map(CourseDTO::new).collect(Collectors.toList());
    }

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
        this.courseList = courseList;
    }
}
