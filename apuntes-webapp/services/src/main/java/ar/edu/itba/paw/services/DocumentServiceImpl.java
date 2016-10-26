package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DocumentDao;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final DocumentDao documentDao;

    @Autowired
    public DocumentServiceImpl(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }


    @Override
    public Document findById(final int documentid) {
        return documentDao.findById(documentid);
    }

    @Override
    public List<Document> findByCourseId(final int courseid) {
        return documentDao.findByCourseId(courseid);
    }

    @Override
    public List<Document> findByClient(final Client uploader) {
        return documentDao.findByClient(uploader);
    }

    @Override
    public List<Document> getAll() {
        return documentDao.getAll();
    }

    @Override
    public Document createDocument(Client user, Course course, String subject, String filename, int filesize, byte[] data) {
        return documentDao.createDocument(user, course, subject, filename, filesize, data);
    }

}
