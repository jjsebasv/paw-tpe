package ar.edu.itba.paw.controllers.admin.models;


import ar.edu.itba.paw.controllers.admin.AbstractCRUDController;
import ar.edu.itba.paw.forms.admin.CourseProgramRelationCreateAdminForm;
import ar.edu.itba.paw.forms.admin.CourseProgramRelationUpdateAdminForm;
import ar.edu.itba.paw.forms.admin.validators.CourseProgramRelationAdminFormValidator;
import ar.edu.itba.paw.interfaces.CourseProgramRelationService;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.CourseProgramRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CourseProgramRelationCRUDController extends AbstractCRUDController<CourseProgramRelation> {


    private final CourseProgramRelationAdminFormValidator courseProgramRelationAdminFormValidator;

    private final CourseProgramRelationService relationService;
    private final ProgramService ps;

    @Autowired
    protected CourseProgramRelationCRUDController(CourseProgramRelationService service, CourseProgramRelationAdminFormValidator courseProgramRelationAdminFormValidator, ProgramService ps) {
        super(service);
        this.courseProgramRelationAdminFormValidator = courseProgramRelationAdminFormValidator;
        this.ps = ps;
        this.relationService = service;
    }

    @RequestMapping(value = "/admin/courseprogramrelation/create", method = {RequestMethod.GET})
    public ModelAndView create(@ModelAttribute("courseProgramRelationCreateForm") final CourseProgramRelationCreateAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/courseprogramrelation.create");

        mav.addObject("programs", ps.getAll());

        return mav;
    }

    @RequestMapping(value = "/admin/courseprogramrelation/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("courseProgramRelationCreateForm") final CourseProgramRelationCreateAdminForm form, final BindingResult errors) {

        courseProgramRelationAdminFormValidator.validate(form, errors);
        if (errors.hasErrors()) {
            return create(form);
        }

        final CourseProgramRelation relation = relationService.addProgramRelationship(form.getCourseid(), form.getProgramid(), form.getSemester());

        return new ModelAndView("redirect:/admin/courseprogramrelation/" + relation.getProgram().getProgramid() + "/" + relation.getCourse().getCourseid() + "/edit");
    }


    @RequestMapping(value = "/admin/courseprogramrelation/{program_pk:[0-9]+}/{course_pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("program_pk") int programPk,
                             @PathVariable("course_pk") int coursePk,
                             @ModelAttribute("courseProgramRelationUpdateForm") final CourseProgramRelationUpdateAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/courseprogramrelation.update");


        final CourseProgramRelation relation = relationService.findById(programPk, coursePk);

        if (relation == null) {
            return new ModelAndView("404");
        }

        form.loadValuesFromInstance(relation);

        mav.addObject("program", relation.getProgram().getName());
        mav.addObject("course", relation.getCourse().getName());

        return mav;
    }


    @RequestMapping(value = "/admin/courseprogramrelation/{program_pk:[0-9]+}/{course_pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("program_pk") int programPk,
                               @PathVariable("course_pk") int coursePk,
                               @Valid @ModelAttribute("courseProgramRelationUpdateForm") final CourseProgramRelationUpdateAdminForm form,
                               @RequestParam("action") String action,
                               final BindingResult errors) {

        if (action.equals("delete")) {
            return delete(programPk, coursePk);
        }

        if (errors.hasErrors()) {
            return read(programPk, coursePk, form);
        }

        final CourseProgramRelation relation = relationService.findById(programPk, coursePk);

        if (relation == null) {
            return new ModelAndView("404");
        }

        relationService.update(coursePk, programPk, form.getSemester());

        return new ModelAndView("redirect:/admin/courseprogramrelation/" + relation.getProgram().getProgramid() + "/" + relation.getCourse().getCourseid() + "/edit");
    }

    private ModelAndView delete(int programPk, int coursePk) {
        relationService.delete(programPk, coursePk);

        return new ModelAndView("redirect:/admin/courseprogramrelation/list");
    }
}
