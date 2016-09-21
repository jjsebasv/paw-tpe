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

@Controller
public class ProgramController {

    private final ProgramService ps;

    private final CourseService cs;

    @Autowired
    public ProgramController(ProgramService ps, CourseService cs) {
        this.ps = ps;
        this.cs = cs;
    }


    @RequestMapping("/program/{id:[\\d]+}")
    public ModelAndView programView(@PathVariable("id") int programid) {

        final ModelAndView mav = new ModelAndView("program");

        Program program = ps.findById(programid);
        if (program == null) {
            //FIXME Add 404 http resp code
            return new ModelAndView("404");
        }

        mav.addObject("program", program);
        mav.addObject("courses", cs.findByProgram(programid));

        return mav;

    }

}
