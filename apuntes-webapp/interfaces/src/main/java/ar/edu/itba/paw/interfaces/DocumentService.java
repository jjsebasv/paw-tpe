package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Document;

import java.util.List;

public interface DocumentService extends GenericCRUDService<Document> {

    List<Document> findByCourseId(long pk);

    List<Document> findByClientId(long pk);

    List<Document> getAll();
}
