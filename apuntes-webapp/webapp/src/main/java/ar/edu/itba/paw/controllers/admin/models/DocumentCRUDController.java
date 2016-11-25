package ar.edu.itba.paw.controllers.admin.models;


import ar.edu.itba.paw.auth.UserPrincipal;
import ar.edu.itba.paw.models.builders.DocumentBuilder;
import ar.edu.itba.paw.controllers.admin.AbstractCRUDController;
import ar.edu.itba.paw.forms.admin.DocumentAdminForm;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DocumentCRUDController extends AbstractCRUDController<Document> {

    private final CourseService cs;

    @Autowired
    public DocumentCRUDController(DocumentService ds, CourseService cs) {
        super(ds);
        this.cs = cs;
    }

    @RequestMapping(value = "/admin/documents/create", method = {RequestMethod.GET})
    public ModelAndView create(@ModelAttribute("documentForm") final DocumentAdminForm form) {
        return new ModelAndView("admin/details/document");
    }

    @RequestMapping(value = "/admin/documents/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("documentForm") final DocumentAdminForm form,
                               @RequestParam CommonsMultipartFile multipartFile,
                               Authentication authentication,
                               final BindingResult errors) {

        if (errors.hasErrors()) {
            return create(form);
        }

        UserPrincipal client = (UserPrincipal) authentication.getPrincipal();

        final Document document = this.service.create(new DocumentBuilder()
                .setUser(client.getClient())
                .setCourse(cs.findById(form.getCourseid()))
                .setSubject(form.getSubject())
                .setDocumentName(multipartFile.getOriginalFilename())
                .setDocumentSize(multipartFile.getSize())
                .setData(multipartFile.getBytes())
                .setDescription(form.getDescription())
                .createModel());

        return new ModelAndView("redirect:/admin/documents/" + document.getDocumentId() + "/edit");
    }


    @RequestMapping(value = "/admin/documents/{pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("pk") int pk, @ModelAttribute("documentForm") final DocumentAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/document");

        final Document document = service.findById(pk);

        if (document == null) {
            return new ModelAndView("404");
        }

        form.loadValuesFromInstance(document);

        mav.addObject("course", document.getCourse());

        mav.addObject("pk", pk);
        return mav;
    }


    @RequestMapping(value = "/admin/documents/{pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("pk") int pk,
                               @Valid @ModelAttribute("documentForm") final DocumentAdminForm form,
                               @RequestParam CommonsMultipartFile multipartFile,
                               @RequestParam("action") String action,
                               final BindingResult errors) {

        if (action.equals("delete")) {
            return delete(pk);
        }

        if (errors.hasErrors()) {
            return read(pk, form);
        }

        final Document document = service.findById(pk);

        if (document == null) {
            return new ModelAndView("404");
        }

        final Document newDocumentData = form.buildObjectFromForm();
        if (!multipartFile.isEmpty()) {
            newDocumentData.setDocumentName(multipartFile.getOriginalFilename());
            newDocumentData.setDocumentSize(multipartFile.getSize());
            newDocumentData.setData(multipartFile.getBytes());
        }
        newDocumentData.setCourse(cs.findById(form.getCourseid()));
        newDocumentData.setUser(document.getUser());
        newDocumentData.setDescription(document.getDescription());

        service.update(pk, newDocumentData);

        return new ModelAndView("redirect:/admin/documents/" + document.getDocumentId() + "/edit");
    }

    private ModelAndView delete(int pk) {
        service.delete(pk);

        return new ModelAndView("redirect:/admin/documents/list");
    }
}
