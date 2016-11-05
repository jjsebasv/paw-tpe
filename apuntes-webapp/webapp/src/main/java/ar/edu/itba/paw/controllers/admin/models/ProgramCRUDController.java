package ar.edu.itba.paw.controllers.admin.models;

import ar.edu.itba.paw.builders.ProgramBuilder;
import ar.edu.itba.paw.controllers.admin.AbstractCRUDController;
import ar.edu.itba.paw.forms.admin.ProgramAdminForm;
import ar.edu.itba.paw.interfaces.ProgramService;
import ar.edu.itba.paw.models.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProgramCRUDController extends AbstractCRUDController<Program> {

    @Autowired
    public ProgramCRUDController(ProgramService ps) {
        super(ps);
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

        final Program program = service.create(new ProgramBuilder().
                setName(form.getName())
                .setShortName(form.getShortName())
                .setGroup(form.getGroup().charAt(0))
                .createModel());

        return new ModelAndView("redirect:/admin/programs/" + program.getProgramid() + "/edit");
    }


    @RequestMapping(value = "/admin/programs/{pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("pk") int programid, @ModelAttribute("programForm") final ProgramAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/program");

        final Program program = service.findById(programid);

        if (program == null) {
            return new ModelAndView("404");
        }

        form.loadValuesFromInstance(program);

        mav.addObject("programid", programid);
        return mav;
    }


    @RequestMapping(value = "/admin/programs/{pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("pk") int programid,
                               @Valid @ModelAttribute("programForm") final ProgramAdminForm form,
                               @RequestParam("action") String action,
                               final BindingResult errors) {

        if (action.equals("delete")) {
            return delete(programid);
        }

        if (errors.hasErrors()) {
            return read(programid, form);
        }

        final Program program = service.findById(programid);

        if (program == null) {
            return new ModelAndView("404");
        }

        service.update(programid, form.buildObjectFromForm());

        return new ModelAndView("redirect:/admin/programs/" + program.getProgramid() + "/edit");
    }

    //@RequestMapping(value = "/admin/programs/{pk:[0-9]+}/delete", method = {RequestMethod.POST})
    private ModelAndView delete(@PathVariable("pk") int programid) {
        service.delete(programid);

        return new ModelAndView("redirect:/admin/programs/list");
    }
}
