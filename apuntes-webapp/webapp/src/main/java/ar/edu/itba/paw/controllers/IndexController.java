package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

@Controller
public class IndexController {
	
	private final UserService us;

	private final CourseService cs;

	@Autowired
	public IndexController(UserService us, CourseService cs) {
		this.us = us;
        this.cs = cs;
	}

	@RequestMapping("/")
	public ModelAndView index() {
		final ModelAndView mav = new ModelAndView("index");
		mav.addObject("courses", cs.getAll());
		return mav;
	}
	
	@RequestMapping("/create")
	public ModelAndView helloWorld(
			@RequestParam(value = "name", required = true) final String username,
			@RequestParam(value = "password", required = true) final String password,
			@RequestParam(value = "repeatPassword", required = true) final String repeatPassword) {
		// TODO: validate password and repeatePassword
		System.out.println("bla");
		final long userid = us.create(username, password);
		return new ModelAndView("redirect:/bla?userId=" + userid);
	}
	
	
}