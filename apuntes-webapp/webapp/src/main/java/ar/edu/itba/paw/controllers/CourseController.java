package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {


    private final CourseService cs;
    private final DocumentService fs;


    @Autowired
    public CourseController(CourseService cs, DocumentService fs) {
        this.cs = cs;
        this.fs = fs;
    }

    @RequestMapping(value = "/course/{code:[\\d]{2}\\.[\\d]{2}}")
    public ModelAndView courseView(@PathVariable("code") String code) {
        final ModelAndView mav = new ModelAndView("course");

        Course course = cs.findByCode(code);
        if (course == null) {
            //FIXME Add 404 http resp code
            return new ModelAndView("404");
        }

        mav.addObject("course", course);
        mav.addObject("files", fs.findByCourseId(course.getCourseid()));

        return mav;
    }

}
