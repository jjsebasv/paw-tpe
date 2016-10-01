package ar.edu.itba.paw.controllers;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import forms.DocumentForm;
import forms.ReviewForm;


@Controller
public class DocumentUploadController {

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
    public ModelAndView uploadDocument() {
        final ModelAndView mav = new ModelAndView("documentUploadView");
        
        mav.addObject("documentForm", new DocumentForm());
//        mav.addObject("courses", courseS.getAll());
        
        return mav;
    }

    @RequestMapping(value = "/uploadDocument/finish", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView submit(@ModelAttribute("documentForm") DocumentForm documentForm, BindingResult result, Model model, @RequestParam CommonsMultipartFile document, HttpServletRequest request) {
      ds.createDocument(clientS.findById(1), courseS.findById(documentForm.getCourseid()), documentForm.getSubject(), document.getOriginalFilename(), (int)document.getSize(), document.getBytes());
      return new ModelAndView("redirect:/uploadDocument");
    }




}
