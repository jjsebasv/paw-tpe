package ar.edu.itba.paw.controllers.admin;

import ar.edu.itba.paw.interfaces.*;
import ar.edu.itba.paw.models.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    private final ProgramService ps;
    private final CourseService cs;
    private final ClientService cls;
    private final DocumentService ds;

    private final static int INITIAL_PAGE = 1;
    private final static int NUMBER_OF_PAGES_TO_SHOW = 5;


    @Autowired
    public AdminController(ProgramService ps, CourseService cs, ClientService cls, DocumentService ds) {
        this.ps = ps;
        this.cs = cs;
        this.cls = cls;
        this.ds = ds;
    }

    @RequestMapping(value = "/admin")
    public ModelAndView indexView() {
        final ModelAndView mav = new ModelAndView("admin/index");

        final Map<String, String> models = new HashMap<>();

        //FIXME Pasar a message.properties
        models.put("Programs", "programs");
        models.put("Courses", "courses");
        models.put("Clients", "clients");
        models.put("Documents", "documents");

        mav.addObject("models", models);

        return mav;
    }

    @RequestMapping(value = "/admin/{model:[a-z]+}/list")
    public ModelAndView listView(@PathVariable("model") String model,
                                 @RequestParam(value = "page", defaultValue = "1", required = false) int page) {
        final ModelAndView mav = new ModelAndView("admin/list/" + model);

        final GenericCRUDService service;

        switch (model) {
            case "programs":
                service = ps;
                break;

            case "courses":
                service = cs;
                break;

            case "clients":
                service = cls;
                break;

            case "documents":
                service = ds;
                break;

            default:
                return new ModelAndView("404");
        }

        PagedResult pagedResult = service.getAll(page);

        if (pagedResult == null) {
            return new ModelAndView("404");
        }

        mav.addObject("model", model);

        List<Integer> pagesToShow = new ArrayList<>(NUMBER_OF_PAGES_TO_SHOW);

        final int initialPage = Math.max(pagedResult.getCurrentPage() - NUMBER_OF_PAGES_TO_SHOW / 2, INITIAL_PAGE);

        final int maxPage = Math.min(pagedResult.getMaxPage(), pagedResult.getCurrentPage() + NUMBER_OF_PAGES_TO_SHOW / 2 + 1);

        for (int i = initialPage; i <= maxPage; i++) {
            pagesToShow.add(i);
        }

        mav.addObject("entries", pagedResult.getResults());
        mav.addObject("page", pagedResult.getCurrentPage());
        mav.addObject("pagesToShow", pagesToShow);
        mav.addObject("maxPage", pagedResult.getMaxPage());

        return mav;
    }
}
