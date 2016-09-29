package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;

import java.io.InputStream;
import java.util.List;

public interface DocumentService {

    Document createFile(InputStream data);

    Document findById(int fileid);

    List<Document> findByCourseId(int courseid);

    List<Document> getAll();

	Document createDocument(Client user, Course course, String subject, String filename, int filesize, byte[] bs);
}
