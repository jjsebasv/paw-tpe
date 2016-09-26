package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Program;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {


    private final CourseService cs;
    private final DocumentService fs;
    public static final String DEFAULT_ERROR_VIEW = "404";


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
            return new ModelAndView(DEFAULT_ERROR_VIEW);
        }

        final List<Document> documents = fs.findByCourseId(course.getCourseid());
        mav.addObject("course", course);
        mav.addObject("documents", documents);
        mav.addObject("documentsSize", documents.size());

        return mav;
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
            ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);

        return mav;
    }

}
