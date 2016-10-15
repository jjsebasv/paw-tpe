package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.forms.ClientForm;
import ar.edu.itba.paw.forms.validators.ClientFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClientController {

    private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final ClientService cs;

    private final ClientFormValidator clientFormValidator;

    @Autowired
    public ClientController(final ClientService cs, final ClientFormValidator clientFormValidator) {
        this.cs = cs;
        this.clientFormValidator = clientFormValidator;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView login(@ModelAttribute("loginForm") final ClientForm form) {
        return new ModelAndView("auth/login");
    }


    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(@ModelAttribute("loginForm") final ClientForm form, final BindingResult errors) {
        if (errors.hasErrors()) {
            return login(form);
        }

        final Client client = cs.findByUsername(form.getUsername());

        if (client.getPassword().equals(form.getPassword())) {
            //TODO LOGIN!
        } else {
            return login(form);
        }

        return new ModelAndView("redirect:/profile");
    }


    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public ModelAndView register(@ModelAttribute("registerForm") final ClientForm form) {
        return new ModelAndView("auth/register");
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public ModelAndView register(@Valid @ModelAttribute("registerForm") final ClientForm form, final BindingResult errors) {
        clientFormValidator.validate(form, errors);
        if (errors.hasErrors()) {
            return register(form);
        }

        final Client client = cs.create(form.getUsername(), form.getPassword(), form.getEmail());
        return new ModelAndView("redirect:/login");
    }

}
