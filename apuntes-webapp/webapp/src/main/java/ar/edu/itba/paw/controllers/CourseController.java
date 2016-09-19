package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.FileService;
import ar.edu.itba.paw.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {


    private final CourseService cs;
    private final FileService fs;


    @Autowired
    public CourseController(CourseService cs, FileService fs) {
        this.cs = cs;
        this.fs = fs;
    }

    @RequestMapping("/courses/{id:[\\d]+}")
    public ModelAndView courseView(@PathVariable("id") long id) {
        final ModelAndView mav = new ModelAndView("course");

        Course course = cs.findById((int) id);
        if(course==null)
            return new ModelAndView("404");


        mav.addObject("course", course);
        mav.addObject("files", fs.findByCourseId((int) id));

        return mav;
    }

}
