package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.Course;
import ar.edu.itba.paw.models.Document;

import java.util.List;

public interface DocumentDao {

    Document findById(int documentid);

    List<Document> findByCourseId(int id);

    List<Document> getAll();

    Document createDocument(Client user, Course course, String subject, String filename, int filesize, byte[] data);
}
