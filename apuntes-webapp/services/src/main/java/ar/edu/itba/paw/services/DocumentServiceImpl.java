package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DocumentDao;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentDao fileDao;

    @Autowired
    public DocumentServiceImpl(DocumentDao fileDao) {
        this.fileDao = fileDao;
    }


    @Override
    public Document createFile(InputStream data) {
        //TODO Validar el nombre para prevenir header injection
        return null;
    }

    @Override
    public Document findById(final int fileid) {
        return fileDao.findById(fileid);
    }

    @Override
    public List<Document> findByCourseId(final int courseid) {
        return fileDao.findByCourseId(courseid);
    }

    @Override
    public List<Document> getAll() {
        return fileDao.getAll();
    }

}
