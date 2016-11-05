package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.UserPrincipal;
import ar.edu.itba.paw.builders.ReviewBuilder;
import ar.edu.itba.paw.forms.ReviewForm;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class DocumentController {

    private final DocumentService fs;
    private final ReviewService rs;


    @Autowired
    public DocumentController(ReviewService rs, DocumentService fs) {
        this.rs = rs;
        this.fs = fs;
    }


    @RequestMapping("/document/{pk:[\\d]+}")
    public ModelAndView courseView(@PathVariable("pk") long pk, Authentication authentication) {
        ModelAndView mav = new ModelAndView("documentView");

        final Document file = fs.findById(pk);
        final List<Review> reviews = rs.findByFileId(pk);
        if (file == null) {
            return new ModelAndView("404");
        }

        final Document doc = fs.findById(pk);

        mav.addObject("document", doc);
        mav.addObject("username", file.getUser().getName());
        mav.addObject("reviewForm", new ReviewForm());
        mav.addObject("reviews", reviews);
        mav.addObject("average", rs.getAverageFromFileId(pk));

        if (authentication == null) {
            mav.addObject("can_review", false);
        } else {
            final Client client = ((UserPrincipal) authentication.getPrincipal()).getClient();
            mav.addObject("can_review", reviews.isEmpty() || !reviews.stream().anyMatch(review -> review.getUser().getClientId() == client.getClientId()));
        }

        return mav;
    }

    @RequestMapping("/download/{pk:[\\d]+}")
    public void downloadFile(HttpServletResponse response, @PathVariable("pk") long pk) throws IOException {

        final Document file = fs.findById(pk);

        //TODO Guardar mimetype para permitirle al navegador decidir
        response.setContentType("application/octet-stream");

        //TODO Validar el nombre para prevenir header injection
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\";", file.getDocumentName()));
        response.setContentLength((int) file.getDocumentSize());
        InputStream data = new ByteArrayInputStream(file.getData());
        FileCopyUtils.copy(data, response.getOutputStream());

    }

    @RequestMapping("/open/{pk:[\\d]+}")
    public void openFile(HttpServletResponse response, @PathVariable("pk") long pk) throws IOException {

        final Document file = fs.findById(pk);

        response.setContentLength((int) file.getDocumentSize());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\";", file.getDocumentName()));

        InputStream data = new ByteArrayInputStream(file.getData());
        FileCopyUtils.copy(data, response.getOutputStream());

    }


    @RequestMapping(value = "/document/{pk:[\\d]+}/addReview", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("reviewForm") ReviewForm reviewForm,
                               BindingResult result,
                               Model model,
                               @PathVariable("pk") long pk,
                               Authentication authentication) {
        Client client = ((UserPrincipal) authentication.getPrincipal()).getClient();

        //FIXME Verificar que el usuario tenga permitido subir un review
        final Document file = fs.findById(pk);

        final Review review = new ReviewBuilder()
                .setFile(file)
                .setUser(client)
                .setReview(reviewForm.getReview())
                .setRanking(reviewForm.getRanking())
                .createModel();

        rs.create(review);

        return new ModelAndView("redirect:/document/" + pk);
    }

    ;

}
