package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DocumentDao;
import ar.edu.itba.paw.interfaces.DocumentService;
import ar.edu.itba.paw.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl extends AbstractCRUDService<Document> implements DocumentService {

    private final DocumentDao documentDao;

    @Autowired
    public DocumentServiceImpl(DocumentDao documentDao) {
        super(documentDao);
        this.documentDao = documentDao;
    }

    @Override
    public void update(long pk, Document from) {
        //TODO
        throw new NotImplementedException();
    }

    @Override
    public List<Document> findByCourseId(final long pk) {
        return documentDao.findByCourseId(pk);
    }

    @Override
    public List<Document> findByClientId(final long pk) {
        return documentDao.findByClientId(pk);
    }
}
