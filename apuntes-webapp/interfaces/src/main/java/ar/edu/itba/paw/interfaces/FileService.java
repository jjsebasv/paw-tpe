package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.File;

import java.io.InputStream;
import java.util.List;

public interface FileService {

    File createFile(InputStream data);

    File findById(int fileid);

    List<File> findByCourseId(int courseid);

    List<File> getAll();
}
