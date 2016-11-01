package ar.edu.itba.paw.controllers.admin.models;

import ar.edu.itba.paw.forms.admin.ProgramAdminForm;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.Program;
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
public class ProgramCRUDController {

    private final ProgramService ps;

    @Autowired
    public ProgramCRUDController(ProgramService ps) {
        this.ps = ps;
    }

    @RequestMapping(value = "/admin/programs/create", method = {RequestMethod.GET})
    public ModelAndView create(@ModelAttribute("programForm") final ProgramAdminForm form) {
        return new ModelAndView("admin/details/program");
    }

    @RequestMapping(value = "/admin/programs/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("programForm") final ProgramAdminForm form, final BindingResult errors) {

        if (errors.hasErrors()) {
            return create(form);
        }

        final Program program = ps.create(form.getName(), form.getShortName(), form.getGroup().charAt(0));
        return new ModelAndView("redirect:/admin/programs/" + program.getProgramid() + "/edit");
    }


    @RequestMapping(value = "/admin/programs/{pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("pk") int programid, @ModelAttribute("programForm") final ProgramAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/program");

        final Program program = ps.findById(programid);

        if (program == null) {
            return new ModelAndView("404");
        }

        form.setName(program.getName());
        form.setShortName(program.getShortName());
        form.setGroup(String.valueOf(program.getGroup()));

        mav.addObject("programid", programid);
        return mav;
    }


    @RequestMapping(value = "/admin/programs/{pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("pk") int programid, @Valid @ModelAttribute("programForm") final ProgramAdminForm form, final BindingResult errors) {

        if (errors.hasErrors()) {
            return read(programid, form);
        }

        final Program program = ps.findById(programid);

        if (program == null) {
            return new ModelAndView("404");
        }

        program.setName(form.getName());
        program.setShortName(form.getShortName());
        program.setGroup(form.getGroup().charAt(0));

        return new ModelAndView("redirect:/admin/programs/" + program.getProgramid() + "/edit");
    }

    @RequestMapping(value = "/admin/programs/{pk:[0-9]+}/delete", method = {RequestMethod.POST})
    public ModelAndView delete(@PathVariable("pk") int programid) {
        final Program program = ps.findById(programid);

        if (program == null) {
            return new ModelAndView("404");
        }

        ps.delete(program);

        return new ModelAndView("redirect:/admin/programs/list");
    }
}
