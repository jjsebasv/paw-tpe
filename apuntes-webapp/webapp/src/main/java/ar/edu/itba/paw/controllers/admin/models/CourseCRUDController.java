package ar.edu.itba.paw.controllers.admin.models;

import ar.edu.itba.paw.forms.admin.CourseAdminForm;
import ar.edu.itba.paw.interfaces.CourseService;
import ar.edu.itba.paw.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CourseCRUDController {

    private final CourseService cs;

    @Autowired
    public CourseCRUDController(CourseService cs) {
        this.cs = cs;
    }

    @RequestMapping(value = "/admin/courses/create", method = {RequestMethod.GET})
    public ModelAndView create(@ModelAttribute("courseForm") final CourseAdminForm form) {
        return new ModelAndView("admin/details/course");
    }

    @RequestMapping(value = "/admin/courses/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("courseForm") final CourseAdminForm form, final BindingResult errors) {

        if (errors.hasErrors()) {
            return create(form);
        }

        final Course course = cs.create(form.getCode(), form.getName());

        return new ModelAndView("redirect:/admin/courses/" + course.getCourseid() + "/edit");
    }


    @RequestMapping(value = "/admin/courses/{pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("pk") int courseid, @ModelAttribute("courseForm") final CourseAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/course");

        final Course course = cs.findById(courseid);

        if (course == null) {
            return new ModelAndView("404");
        }

        form.setName(course.getName());
        form.setCode(course.getCode());

        mav.addObject("courseid", courseid);
        return mav;
    }


    @RequestMapping(value = "/admin/courses/{pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("pk") int courseid, @Valid @ModelAttribute("courseForm") final CourseAdminForm form, final BindingResult errors) {

        if (errors.hasErrors()) {
            return read(courseid, form);
        }

        final Course course = cs.findById(courseid);

        if (course == null) {
            return new ModelAndView("404");
        }

        course.setCode(form.getCode());
        course.setName(form.getName());

        return new ModelAndView("redirect:/admin/courses/" + course.getCourseid() + "/edit");
    }

    @RequestMapping(value = "/admin/courses/{pk:[0-9]+}/delete", method = {RequestMethod.POST})
    public ModelAndView delete(@PathVariable("pk") int courseid) {
        final Course course = cs.findById(courseid);

        if (course == null) {
            return new ModelAndView("404");
        }

        cs.delete(course);

        return new ModelAndView("redirect:/admin/courses/list");
    }
}
