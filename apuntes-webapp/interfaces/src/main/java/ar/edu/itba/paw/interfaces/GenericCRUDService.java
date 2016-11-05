package ar.edu.itba.paw.interfaces;

import java.util.List;

public interface GenericCRUDService<T> {

    T create(T b);

    T findById(long pk);

    void update(long pk, T from);

    void delete(long pk);

    List<T> getAll();
}
