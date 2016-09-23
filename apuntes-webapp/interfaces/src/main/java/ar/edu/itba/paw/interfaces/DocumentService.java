package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Client;

import java.io.InputStream;
import java.util.List;

public interface DocumentService {

    Document createFile(InputStream data);

    Document findById(int fileid);

    List<Document> findByCourseId(int courseid);

    List<Document> getAll();
}
