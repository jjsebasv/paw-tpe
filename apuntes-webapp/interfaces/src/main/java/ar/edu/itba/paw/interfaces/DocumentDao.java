package ar.edu.itba.paw.interfaces;

import java.io.InputStream;
import java.util.List;

import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Client;

public interface DocumentDao {

    Document createFile(InputStream data);

    Document findById(int fileid);

    List<Document> findByCourseId(int id);

    List<Document> getAll();
}
