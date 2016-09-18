package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.CourseDao;
import ar.edu.itba.paw.interfaces.FileDao;
import ar.edu.itba.paw.interfaces.FileService;
import ar.edu.itba.paw.models.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FileDao fileDao;

    @Autowired
    public FileServiceImpl(FileDao fileDao) {
        this.fileDao = fileDao;
    }


    @Override
    public File createFile(InputStream data) {
        //TODO Validar el nombre para prevenir header injection
        return null;
    }

    @Override
    public File findById(final int fileid) {
        return fileDao.findById(fileid);
    }

    @Override
    public List<File> findByCourseId(final int courseid) {
        return fileDao.findByCourseId(courseid);
    }

    @Override
    public List<File> getAll() {
        return fileDao.getAll();
    }
}
