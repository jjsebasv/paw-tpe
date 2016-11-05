package ar.edu.itba.paw.controllers.admin.models;

import ar.edu.itba.paw.builders.ClientBuilder;
import ar.edu.itba.paw.controllers.admin.AbstractCRUDController;
import ar.edu.itba.paw.forms.admin.ClientAdminForm;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClientCRUDController extends AbstractCRUDController<Client> {

    private static final String CLIENT_DETAILS_VIEW = "admin/details/client";

    @Autowired
    public ClientCRUDController(final ClientService cs) {
        super(cs);
    }

    @RequestMapping(value = "/admin/clients/create", method = {RequestMethod.GET})
    public ModelAndView create(@ModelAttribute("clientForm") final ClientAdminForm form) {
        return new ModelAndView(CLIENT_DETAILS_VIEW);
    }

    @RequestMapping(value = "/admin/clients/create", method = {RequestMethod.POST})
    public ModelAndView create(@Valid @ModelAttribute("clientForm") final ClientAdminForm form, final BindingResult errors) {

        if (errors.hasErrors()) {
            return create(form);
        }

        final Client client = service.create(new ClientBuilder().
                setName(form.getUsername()).
                setEmail(form.getEmail()).
                setPassword(form.getPassword()).
                createModel());

        return new ModelAndView("redirect:/admin/clients/" + client.getClientId() + "/edit");
    }


    @RequestMapping(value = "/admin/clients/{pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("pk") int clientid, @ModelAttribute("clientForm") final ClientAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/client");

        final Client client = service.findById(clientid);

        if (client == null) {
            return new ModelAndView("404");
        }

        form.loadValuesFromInstance(client);

        mav.addObject("clientid", clientid);
        return mav;
    }


    @RequestMapping(value = "/admin/clients/{pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("pk") int clientid,
                               @Valid @ModelAttribute("clientForm") final ClientAdminForm form,
                               @RequestParam("action") String action,
                               final BindingResult errors) {

        if (action.equals("delete")) {
            return delete(clientid);
        }

        if (errors.hasErrors()) {
            return read(clientid, form);
        }

        final Client client = service.findById(clientid);

        if (client == null) {
            return new ModelAndView("404");
        }

        service.update(clientid, form.buildObjectFromForm());

        return new ModelAndView("redirect:/admin/clients/" + client.getClientId() + "/edit");
    }

    private ModelAndView delete(@PathVariable("pk") int clientid) {
        service.delete(clientid);

        return new ModelAndView("redirect:/admin/clients/list");
    }
}
