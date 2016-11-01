package ar.edu.itba.paw.controllers.admin;

import ar.edu.itba.paw.interfaces.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    private final ProgramService ps;

    @Autowired
    public AdminController(ProgramService ps) {
        this.ps = ps;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView indexView() {
        final ModelAndView mav = new ModelAndView("admin/index");

        final Map<String, String> models = new HashMap<>();

        //FIXME Pasar a message.properties
        models.put("Programs", "programs");

        mav.addObject("models", models);

        return mav;
    }

    @RequestMapping(value = "/admin/{model:[a-z]+}/list")
    public ModelAndView listView(@PathVariable("model") String model) {
        final ModelAndView mav;


        //TODO START,LIMIT
        switch (model) {
            case "programs":
                mav = new ModelAndView("admin/list/programs");
                mav.addObject("entries", ps.getAll());
                break;

            default:
                return new ModelAndView("404");
        }

        return mav;
    }

}
