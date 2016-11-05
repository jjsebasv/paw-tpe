package ar.edu.itba.paw.interfaces.admin;

public interface IAdminForm<T> {

    T buildObjectFromForm();

    void loadValuesFromInstance(final T instance);

}
