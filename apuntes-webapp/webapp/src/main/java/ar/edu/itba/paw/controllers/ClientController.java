package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.PawUserDetailsService;
import ar.edu.itba.paw.auth.UserPrincipal;
import ar.edu.itba.paw.forms.ChangePasswordForm;
import ar.edu.itba.paw.forms.ClientForm;
import ar.edu.itba.paw.forms.validators.ChangePasswordFormValidator;
import ar.edu.itba.paw.forms.validators.ClientFormValidator;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.models.builders.ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {

    private final ClientService cs;
    private final DocumentService ds;
    private final ReviewService rs;

    private final PawUserDetailsService userDetailsService;

    @Qualifier("authenticationManager")
    private final AuthenticationManager authenticationManager;

    private final ClientFormValidator clientFormValidator;
    private final ChangePasswordFormValidator changePasswordFormValidator;

    private final Validator validator;

    @Autowired
    public ClientController(final ClientService cs, final DocumentService ds, final ReviewService rs, final ClientFormValidator clientFormValidator, AuthenticationManager authenticationManager, PawUserDetailsService userDetailsService, ChangePasswordFormValidator changePasswordFormValidator, @Qualifier("mvcValidator") Validator validator) {
        this.cs = cs;
        this.ds = ds;
        this.rs = rs;
        this.clientFormValidator = clientFormValidator;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.changePasswordFormValidator = changePasswordFormValidator;
        this.validator = validator;
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

        final Client client = cs.create(new ClientBuilder()
                .setName(form.getUsername())
                .setPassword(form.getPassword())
                .setEmail(form.getEmail())
                .createModel());


        UserDetails userDetails = userDetailsService.loadUserByUsername(client.getName());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, client.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(auth);

        // redirect into secured main page if authentication successful
        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            return new ModelAndView("redirect:/profile");
        }

        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.GET})
    public ModelAndView profile(Authentication authentication) {
        final ModelAndView mav = new ModelAndView("profile");

        UserPrincipal client = (UserPrincipal) authentication.getPrincipal();

        mav.addObject("client", client.getClient());
        final List<Document> documents = ds.findByClientId(client.getClient().getClientId());
        mav.addObject("documents", documents);
        mav.addObject("documentsSize", documents.size());

        //TODO Estamos teniendo problemas para subir review
        final List<Review> reviews = rs.findByUserId(client.getClient().getClientId());
        mav.addObject("reviews", reviews);
        mav.addObject("reviewsSize", reviews.size());

        return mav;
    }

    @RequestMapping(value = "/profile/change_password", method = {RequestMethod.GET})
    public ModelAndView changePassword(@ModelAttribute("changePasswordForm") final ChangePasswordForm form,
                                       Authentication authentication) {
        final ModelAndView mav = new ModelAndView("changePassword");

        UserPrincipal client = (UserPrincipal) authentication.getPrincipal();

        mav.addObject("client", client.getClient());

        return mav;
    }

    @RequestMapping(value = "/profile/change_password", method = {RequestMethod.POST})
    public ModelAndView changePassword(@ModelAttribute("changePasswordForm") final ChangePasswordForm form,
                                       Authentication authentication,
                                       final BindingResult errors) {
        UserPrincipal clientPrincipal = (UserPrincipal) authentication.getPrincipal();
        final Client client = clientPrincipal.getClient();

        form.setClient(client);

        validator.validate(form, errors);
        changePasswordFormValidator.validate(form, errors);
        if (errors.hasErrors()) {
            return changePassword(form, authentication);
        }

        client.setPassword(form.getNewPassword());

        cs.update(client.getClientId(), client);

        return new ModelAndView("redirect:/profile");
    }

}
