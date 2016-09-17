package ar.edu.itba.paw.interfaces;

import java.util.List;

import ar.edu.itba.paw.models.File;

public interface FileDao {

    File createFile(byte[] data);

    File findById(int fileid);

    List<File> findByCourseId(int id);

    List<File> getAll();
}
