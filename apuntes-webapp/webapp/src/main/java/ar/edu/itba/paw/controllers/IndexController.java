package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.forms.DocumentForm;
import ar.edu.itba.paw.interfaces.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private final ProgramService ps;

    @Autowired
    public IndexController(final ProgramService ps) {
        this.ps = ps;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("programs", ps.getAll());
        mav.addObject("documentForm", new DocumentForm());
        return mav;
    }

}
