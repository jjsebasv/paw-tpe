package ar.edu.itba.paw.interfaces;

public interface GenericCRUDService<T> {

    T create(T e);

    T findById(int pk);

    void update(int pk, T from);

    void delete(int pk);
}
