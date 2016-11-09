package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.PagedResult;

import java.util.List;

public interface GenericCRUDDao<T> {

    T create(T b);

    T findById(long pk);

    void delete(long pk);

    List<T> getAll();

    PagedResult<T> getAll(int start, int limit);

    int count();
}
