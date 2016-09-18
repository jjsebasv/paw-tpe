package ar.edu.itba.paw.interfaces;

import java.io.InputStream;
import java.util.List;

import ar.edu.itba.paw.models.File;
import ar.edu.itba.paw.models.User;

public interface FileDao {

    File createFile(InputStream data);

    File findById(int fileid);

    List<File> findByCourseId(int id);

    List<File> getAll();

    User getUser(int userid);
}
