package ar.edu.itba.paw.controllers.api;

import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ApiCourseController {

    private final CourseService cs;

    @Autowired
    public ApiCourseController(CourseService cs) {
        this.cs = cs;
    }

    @RequestMapping(value = "/api/course/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, String>> courseView(@RequestParam(value = "term", defaultValue = "", required = false) String name) {

        List<Course> courses;
        if (name.isEmpty()) {
            courses = cs.getAll();
        } else {
            courses = cs.findByName(name);
        }

        return courses.parallelStream().
                map(
                        course -> new HashMap<String, String>() {{
                            put("id", String.valueOf(course.getCourseid()));
                            put("text", String.format("%s - %s", course.getCode(), course.getName()));
                        }}
                )
                .collect(Collectors.toList());
    }

}
