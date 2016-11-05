package ar.edu.itba.paw.controllers.admin;

import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.interfaces.CourseService;
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
    private final CourseService cs;

    private final ClientService cls;

    @Autowired
    public AdminController(ProgramService ps, CourseService cs, ClientService cls) {
        this.ps = ps;
        this.cs = cs;
        this.cls = cls;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView indexView() {
        final ModelAndView mav = new ModelAndView("admin/index");

        final Map<String, String> models = new HashMap<>();

        //FIXME Pasar a message.properties
        models.put("Programs", "programs");
        models.put("Courses", "courses");
//        models.put("Clients", "clients");

        mav.addObject("models", models);

        return mav;
    }

    @RequestMapping(value = "/admin/{model:[a-z]+}/list")
    public ModelAndView listView(@PathVariable("model") String model) {
        final ModelAndView mav = new ModelAndView("admin/list/" + model);

        //TODO START,LIMIT para paginacion
        switch (model) {
            case "programs":
                mav.addObject("entries", ps.getAll());
                break;

            case "courses":
                mav.addObject("entries", cs.getAll());
                break;

            case "clients":
                mav.addObject("entries", cls.getAll());
                break;

            default:
                return new ModelAndView("404");
        }

        return mav;
    }
}
