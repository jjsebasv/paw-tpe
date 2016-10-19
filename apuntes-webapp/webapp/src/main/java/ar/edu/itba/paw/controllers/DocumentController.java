package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.auth.UserPrincipal;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.forms.ReviewForm;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;

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


    @RequestMapping("/document/{id:[\\d]+}")
    public ModelAndView courseView(@PathVariable("id") int id, Authentication authentication) {
        ModelAndView mav = new ModelAndView("documentView");

        final Document file = fs.findById(id);
        final List<Review> reviews = rs.findByFileId(id);
        if (file == null) {
            return new ModelAndView("404");
        }

        mav.addObject("document", fs.findById(id));
        mav.addObject("username", file.getUser().getName());
        mav.addObject("reviewForm", new ReviewForm());
        mav.addObject("reviews", reviews);
        mav.addObject("average", rs.getAverage(id));

        if (authentication == null) {
            mav.addObject("can_review", false);
        } else {
            final Client client = ((UserPrincipal) authentication.getPrincipal()).getClient();
            mav.addObject("can_review", reviews.isEmpty() || !reviews.stream().anyMatch(review -> review.getUser().getClientId() == client.getClientId()));
        }

        return mav;
    }

    @RequestMapping("/download/{id:[\\d]+}")
    public void downloadFile(HttpServletResponse response, @PathVariable("id") int id) throws IOException {

        final Document file = fs.findById(id);

        //TODO Guardar mimetype para permitirle al navegador decidir
        response.setContentType("application/octet-stream");

        //TODO Validar el nombre para prevenir header injection
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\";", file.getDocumentName()));
        response.setContentLength(file.getDocumentSize());
        InputStream data = file.getData();
        FileCopyUtils.copy(data, response.getOutputStream());

    }

    @RequestMapping("/open/{id:[\\d]+}")
    public void openFile(HttpServletResponse response, @PathVariable("id") int id) throws IOException {

        final Document file = fs.findById(id);

        response.setContentLength(file.getDocumentSize());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\";", file.getDocumentName()));

        InputStream data = file.getData();
        FileCopyUtils.copy(data, response.getOutputStream());

    }


    @RequestMapping(value = "/document/{id:[\\d]+}/addReview", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("reviewForm") ReviewForm reviewForm,
                               BindingResult result,
                               Model model,
                               @PathVariable("id") int fileid,
                               Authentication authentication) {
        Client client = ((UserPrincipal) authentication.getPrincipal()).getClient();

        //FIXME Verificar que el usuario tenga permitido subir un review
        final Document file = fs.findById(fileid);


        rs.createReview(file, client, reviewForm.getRanking(), reviewForm.getReview());

        return new ModelAndView("redirect:/document/" + fileid);
    }

    ;

}
