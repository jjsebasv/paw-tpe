package ar.edu.itba.paw.controllers;


import ar.edu.itba.paw.auth.UserPrincipal;
import ar.edu.itba.paw.builders.DocumentBuilder;
import ar.edu.itba.paw.forms.DocumentForm;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
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
import javax.validation.Valid;


@Controller
public class DocumentUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentUploadController.class);

    private final CourseService courseS;

    private final DocumentService ds;

    @Autowired
    public DocumentUploadController(CourseService courseS, DocumentService ds) {
        this.courseS = courseS;
        this.ds = ds;
    }

    @RequestMapping(value = "/uploadDocument")
    public ModelAndView uploadDocument(@ModelAttribute("documentForm") DocumentForm documentForm,
                                       @RequestParam(value = "course", defaultValue = "", required = false) String courseCode) {

        ModelAndView mav = new ModelAndView("documentUploadView");

        final Course course = courseS.findByCode(courseCode);

        if (course == null) {
            return new ModelAndView("404");
        }

        mav.addObject("course", course);


        return mav;
    }

    @RequestMapping(value = "/uploadDocument/finish", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView submit(@Valid @ModelAttribute("documentForm") DocumentForm documentForm,
                               BindingResult result,
                               Model model,
                               @RequestParam CommonsMultipartFile multipartFile,
                               HttpServletRequest request,
                               Authentication authentication,
                               final BindingResult errors) {


        if (errors.hasErrors()) {
            return uploadDocument(documentForm, "");
        }

        UserPrincipal client = (UserPrincipal) authentication.getPrincipal();

        final Document document = ds.create(new DocumentBuilder()
                .setUser(client.getClient())
                .setCourse(courseS.findById(documentForm.getCourseid()))
                .setSubject(documentForm.getSubject())
                .setDocumentName(multipartFile.getOriginalFilename())
                .setDocumentSize(multipartFile.getSize())
                .setData(multipartFile.getBytes())
                .createModel());

        LOGGER.info("Uploaded document {}", document);
        return new ModelAndView("redirect:/document/" + document.getDocumentId());

    }


}
