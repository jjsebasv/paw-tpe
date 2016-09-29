package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.ProgramService;
import forms.DocumentForm;
import forms.ReviewForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.ClientService;

@Controller
public class IndexController {

    //private final UserService us;

    private final ProgramService ps;

    @Autowired
    public IndexController(/*UserService us,*/ ProgramService ps) {
        //this.us = us;
        this.ps = ps;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("programs", ps.getAll());
        mav.addObject("documentForm", new DocumentForm());
        return mav;
    }
//
//    @RequestMapping("/create")
//    public ModelAndView helloWorld(
//            @RequestParam(value = "name", required = true) final String username,
//            @RequestParam(value = "password", required = true) final String password,
//            @RequestParam(value = "repeatPassword", required = true) final String repeatPassword) {
//        // TODO: validate password and repeatePassword
//        final long userid = us.create(username, password);
//        return new ModelAndView("redirect:/bla?userId=" + userid);
//    }


}
