package ar.edu.itba.paw.controllers.admin.models;

import ar.edu.itba.paw.controllers.admin.AbstractCRUDController;
import ar.edu.itba.paw.forms.admin.ReviewAdminForm;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ReviewCRUDController extends AbstractCRUDController<Review> {

    @Autowired
    public ReviewCRUDController(ReviewService rs) {
        super(rs);
    }

    @RequestMapping(value = "/admin/reviews/{pk:[0-9]+}/edit", method = {RequestMethod.GET})
    public ModelAndView read(@PathVariable("pk") int pk, @ModelAttribute("reviewForm") final ReviewAdminForm form) {
        final ModelAndView mav = new ModelAndView("admin/details/review.update");

        final Review review = service.findById(pk);

        if (review == null) {
            return new ModelAndView("404");
        }

        form.loadValuesFromInstance(review);

        mav.addObject("review", review);
        mav.addObject("pk", pk);
        return mav;
    }

    @RequestMapping(value = "/admin/reviews/{pk:[0-9]+}/edit", method = {RequestMethod.POST})
    public ModelAndView update(@PathVariable("pk") int pk,
                               @Valid @ModelAttribute("reviewForm") final ReviewAdminForm form,
                               @RequestParam("action") String action,
                               final BindingResult errors) {

        if (action.equals("delete")) {
            return delete(pk);
        }

        if (errors.hasErrors()) {
            return read(pk, form);
        }

        final Review review = service.findById(pk);

        if (review == null) {
            return new ModelAndView("404");
        }

        service.update(pk, form.buildObjectFromForm());

        return new ModelAndView("redirect:/admin/reviews/" + review.getReviewid() + "/edit");
    }

    private ModelAndView delete(int pk) {
        service.delete(pk);

        return new ModelAndView("redirect:/admin/reviews/list");
    }
}
