package ar.edu.itba.paw.controllers;


import ar.edu.itba.paw.auth.UserPrincipal;
import ar.edu.itba.paw.forms.DocumentForm;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class DocumentUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentUploadController.class);

    private final DocumentService ds;
    private final ClientService clientS;
    private final CourseService courseS;

    @Autowired
    public DocumentUploadController(DocumentService ds, ClientService clientS, CourseService courseS) {
        this.ds = ds;
        this.clientS = clientS;
        this.courseS = courseS;
    }

    @RequestMapping(value = "/uploadDocument")
    public ModelAndView uploadDocument(Authentication authentication) {
        final ModelAndView mav = new ModelAndView("documentUploadView");

        UserPrincipal client = (UserPrincipal) authentication.getPrincipal();

        LOGGER.warn("About to upload a document as {}", client);

        mav.addObject("documentForm", new DocumentForm());
//        mav.addObject("courses", courseS.getAll());

        return mav;
    }

    @RequestMapping(value = "/uploadDocument/finish", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView submit(@ModelAttribute("documentForm") DocumentForm documentForm, BindingResult result, Model model, @RequestParam CommonsMultipartFile document, HttpServletRequest request) {
        ds.createDocument(clientS.findById(1), courseS.findById(documentForm.getCourseid()), documentForm.getSubject(), document.getOriginalFilename(), (int) document.getSize(), document.getBytes());
        return new ModelAndView("redirect:/uploadDocument");
    }


}
