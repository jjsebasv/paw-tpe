package ar.edu.itba.paw.interfaces;

import java.io.InputStream;
import java.util.List;

import ar.edu.itba.paw.models.Document;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;

public interface DocumentDao {

    Document createFile(InputStream data);

    Document findById(int fileid);

    List<Document> findByCourseId(int id);

    List<Document> getAll();

	Document createDocument(Client user, Course course, String subject, String filename, int filesize,
			byte[] data);
}
