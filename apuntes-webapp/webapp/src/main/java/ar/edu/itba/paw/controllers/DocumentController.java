package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.interfaces.ReviewService;
import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Client;
import forms.ReviewForm;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class DocumentController {

    private final DocumentService fs;
    private final ReviewService rs;


    @Autowired
    public DocumentController(ReviewService rs, DocumentService fs) {
        this.fs = fs;
        this.rs = rs;
    }


    @RequestMapping("/document/{id:[\\d]+}")
    public ModelAndView courseView(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("documentView");

        final Document file = fs.findById(id);

        if(file==null)
            return new ModelAndView("404");

        mav.addObject("document", fs.findById(id));
        mav.addObject("username", file.getUser().getName());
        mav.addObject("reviewForm", new ReviewForm());
        mav.addObject("reviews", rs.findByFileId((int) id));
        mav.addObject("average", rs.getAverage(id));

        return mav;
    }

    @RequestMapping("/download/{id:[\\d]+}")
    //FIXME Preguntar por la exception
    public void downloadFile(HttpServletResponse response, @PathVariable("id") int id) throws IOException {

        final Document file = fs.findById(id);

        //TODO Guardar mimetype para permitirle al navegador decidir
        response.setContentType("application/octet-stream");

        //TODO Validar el nombre para prevenir header injection
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\";", file.getDocumentName()));
        response.setContentLength(file.getDocumentSize());

        FileCopyUtils.copy(file.getData(), response.getOutputStream());

    }

    @RequestMapping(value = "/document/{id:[\\d]+}/addReview", method = RequestMethod.POST)
    public ModelAndView submit(@ModelAttribute("reviewForm") ReviewForm reviewForm, BindingResult result, Model model, @PathVariable("id") int fileid) {

        final Document file = fs.findById(fileid);
        final Client user = new Client(1, "usuario cableado", "1234"); //FIXME Para cuando haya manejo de sesion

        rs.createReview(file, user, reviewForm.getRanking(), reviewForm.getReview());

        return new ModelAndView("redirect:/document/" + fileid);
    };

}
