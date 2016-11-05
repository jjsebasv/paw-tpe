package ar.edu.itba.paw.controllers.admin.models;

import ar.edu.itba.paw.builders.CourseBuilder;
import ar.edu.itba.paw.controllers.admin.AbstractCRUDController;
import ar.edu.itba.paw.forms.admin.CourseAdminForm;
import ar.edu.itba.paw.forms.admin.validators.CourseAdminFormValidator;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CourseCRUDController extends AbstractCRUDController<Course> {

    private final CourseAdminFormValidator courseAdminFormValidator;

    @Autowired
    public CourseCRUDController(CourseService cs, CourseAdminFormValidator courseAdminFormValidator) {
        super(cs);
        this.courseAdminFormValidator = courseAdminFormValidator;
    }

    @RequestMapping(value = "/admin/courses/create", method = {RequestMethod.GET})
    public ModelAndView create(@ModelAttribute("courseForm") final CourseAdminForm form) {
        return new ModelAndView("admin/details/course");
    }

    @RequestMapping(value = "/admin/courses/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("courseForm") final CourseAdminForm form, final BindingResult errors) {

        courseAdminFormValidator.validate(form, errors);
        if (errors.hasErrors()) {
            return create(form);
        }

        final Course course = service.create(new CourseBuilder()
                .setCode(form.getCode())
                .setName(form.getName())
                .createModel());

        return new ModelAndView("redirect:/admin/courses/" + course.getCourseid() + "/edit");
    }


    @RequestMapping(value = "/admin/courses/{pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("pk") int courseid, @ModelAttribute("courseForm") final CourseAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/course");

        final Course course = service.findById(courseid);

        if (course == null) {
            return new ModelAndView("404");
        }

        form.loadValuesFromInstance(course);

        mav.addObject("courseid", courseid);
        return mav;
    }


    @RequestMapping(value = "/admin/courses/{pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("pk") int courseid,
                               @Valid @ModelAttribute("courseForm") final CourseAdminForm form,
                               @RequestParam("action") String action,
                               final BindingResult errors) {

        if (action.equals("delete")) {
            return delete(courseid);
        }

        //courseAdminFormValidator.validate(form, errors);
        if (errors.hasErrors()) {
            return read(courseid, form);
        }

        final Course course = service.findById(courseid);

        if (course == null) {
            return new ModelAndView("404");
        }

        service.update(courseid, form.buildObjectFromForm());

        return new ModelAndView("redirect:/admin/courses/" + course.getCourseid() + "/edit");
    }

    //@RequestMapping(value = "/admin/courses/{pk:[0-9]+}/delete", method = {RequestMethod.POST})
    private ModelAndView delete(@PathVariable("pk") int courseid) {
        service.delete(courseid);

        return new ModelAndView("redirect:/admin/courses/list");
    }
}
