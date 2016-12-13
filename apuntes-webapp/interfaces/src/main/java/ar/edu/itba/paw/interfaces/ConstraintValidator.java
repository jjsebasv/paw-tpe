package ar.edu.itba.paw.interfaces;

public interface ConstraintValidator<T> {

    //FIXME Ver que exception tirar
    void validate(T dto) throws Exception;

}
