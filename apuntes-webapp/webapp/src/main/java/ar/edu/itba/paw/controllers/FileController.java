package ar.edu.itba.paw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.FileDao;
import ar.edu.itba.paw.models.File;

@Controller
public class FileController {
	
	@Autowired
	private FileDao fd;

	@RequestMapping("/file/{id:[\\d]+}")
	public ModelAndView courseView(@PathVariable("id") int id) {
		final ModelAndView mav = new ModelAndView("fileList");
		mav.addObject("onefile", fd.findById(id));
		return mav;
	}

	/*@RequestMapping("/fileList")
	public ModelAndView fileView(@RequestParam(value = "fileid", required = false, defaultValue = "-1") final int fileid) {
		final ModelAndView mav = new ModelAndView("fileList");
		if (fileid != -1) {
			mav.addObject("onefile", fd.findById(fileid));
		} else {
			List<File> auxList = fd.getAll();
			for(File file : auxList) {
				mav.addObject("onefile", file);
			}
		}

		return mav;
	}*/



}
