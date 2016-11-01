package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.UserPrincipal;
import ar.edu.itba.paw.forms.ClientForm;
import ar.edu.itba.paw.forms.validators.ClientFormValidator;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {

    private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final ClientService cs;

    private final DocumentService ds;

    private final ReviewService rs;

    private final ClientFormValidator clientFormValidator;

    @Autowired
    public ClientController(final ClientService cs, final DocumentService ds, final ReviewService rs, final ClientFormValidator clientFormValidator) {
        this.cs = cs;
        this.ds = ds;
        this.rs = rs;
        this.clientFormValidator = clientFormValidator;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("auth/login");

        return model;
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

    @RequestMapping(value= "/profile", method = {RequestMethod.GET})
    public ModelAndView profile(Authentication authentication){
        final ModelAndView mav = new ModelAndView("profile");

        UserPrincipal client = (UserPrincipal) authentication.getPrincipal();

        mav.addObject("client", client.getClient());
        final List<Document> documents = ds.findByClient(client.getClient());
        mav.addObject("documents", documents);
        mav.addObject("documentsSize", documents.size());

        //TODO Estamos teniendo problemas para subir review
        final List<Review> reviews = rs.findByUser(client.getClient().getClientId());
        mav.addObject("reviews", reviews);
        mav.addObject("reviewsSize", reviews.size());
        
        return mav;
    }

}
