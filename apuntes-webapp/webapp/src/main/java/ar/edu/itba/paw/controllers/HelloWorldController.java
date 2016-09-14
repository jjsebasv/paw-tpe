package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

@Controller
public class HelloWorldController {

	@Autowired
	private UserService us;
	
	@RequestMapping("/bla")
	public ModelAndView helloWorld(@RequestParam(value = "userId", required = true) final int userid) {
		final ModelAndView mav = new ModelAndView("index");
		mav.addObject("greeting", us.findById(userid));
		
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
