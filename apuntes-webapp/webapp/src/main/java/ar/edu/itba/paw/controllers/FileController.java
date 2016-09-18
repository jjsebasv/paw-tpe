package ar.edu.itba.paw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.FileDao;
import ar.edu.itba.paw.models.File;

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
        final ModelAndView mav = new ModelAndView("fileView");
        mav.addObject("file", fd.findById(id));
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
        response.setContentLength(file.getFileSize());


        FileCopyUtils.copy(file.getData(), response.getOutputStream());

    }

}
