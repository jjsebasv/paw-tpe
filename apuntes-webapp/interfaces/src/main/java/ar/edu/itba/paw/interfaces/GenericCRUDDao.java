package ar.edu.itba.paw.interfaces;

import java.util.List;

public interface GenericCRUDDao<T> {

    T create(T b);

    T findById(long pk);

    void delete(long pk);

    List<T> getAll();
}
