package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProgramController {

    private final ProgramService ps;

    private final CourseService cs;

    @Autowired
    public ProgramController(ProgramService ps, CourseService cs) {
        this.ps = ps;
        this.cs = cs;
    }


    @RequestMapping("/program/{pk:[\\d]+}")
    public ModelAndView programView(@PathVariable("pk") int pk) {

        final ModelAndView mav = new ModelAndView("program");

        Program program = ps.findById(pk);
        if (program == null) {
            //FIXME Add 404 http resp code
            return new ModelAndView("404");
        }

        final Map<Integer, List<Course>> groupedCourses = cs.findByProgramId(pk);

        int coursesCount = 0;

        for (List<Course> courseList : groupedCourses.values()) {
            coursesCount += courseList.size();
        }

        final List<Course> optativas = groupedCourses.remove(0);

        mav.addObject("program", program);
        mav.addObject("courses", groupedCourses);
        mav.addObject("optativas", optativas);
        mav.addObject("coursesSize", coursesCount);

        return mav;

    }

}
