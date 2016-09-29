package ar.edu.itba.paw.controllers.api;

import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ApiCourseController {

    private final CourseService cs;

    @Autowired
    public ApiCourseController(CourseService cs) {
        this.cs = cs;
    }


    @RequestMapping(value = "/api/course/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> courseView(@RequestParam(value = "name", defaultValue = "", required = false) String name) {

        List<Course> courses;
        if (name.isEmpty()) {
            courses = cs.getAll();
        } else {
            courses = cs.findByName(name);
        }

        return courses;
    }

}
