package ar.edu.itba.paw.controllers;

import ar.edu.itba.paw.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.FileDao;
import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.Review;
import ar.edu.itba.paw.persistance.FileJdbcDao;

import javax.crypto.Mac;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileController {

    private final FileDao fd;


    @Autowired
    public FileController(FileDao fd) {
        this.fd = fd;
    }


    @RequestMapping("/file/{id:[\\d]+}")
    public ModelAndView courseView(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("fileView");
        try {
        	final File file = fd.findById(id);
        	mav.addObject("file", fd.findById(id));
        	mav.addObject("username", fd.getUser(file.getUserid()).getName() );
        } catch (Exception e) {
			mav = new ModelAndView("404");
		}     
        return mav;
    }

    @RequestMapping("/download/{id:[\\d]+}")
    //FIXME Preguntar por la exception
    public void downloadFile(HttpServletResponse response, @PathVariable("id") int id) throws IOException {

        final File file = fd.findById(id);

        //TODO Guardar mimetype para permitirle al navegador decidir
        response.setContentType("application/octet-stream");

        //TODO Validar el nombre para prevenir header injection
        //FIXME Ver la necesidad del uso de trim()
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\";", file.getFileName().trim()));

        FileCopyUtils.copy(file.getData(), response.getOutputStream());

    }
    
    @RequestMapping(value = "/file/{id:[\\d]+}/addReview", method = RequestMethod.POST)
    public String submit(@ModelAttribute("fileView")Review review, BindingResult result, ModelMap model,@PathVariable("id") int id) {
        
        model.addAttribute("fileid", id);
        model.addAttribute("ranking", review.getRanking());
        model.addAttribute("review", review.getReview());
        model.addAttribute("reviewid", 1);
        model.addAttribute("userid", fd.findById(id).getUserid());
        System.out.println(review.getRanking());
        return "fileView";
    }

}
