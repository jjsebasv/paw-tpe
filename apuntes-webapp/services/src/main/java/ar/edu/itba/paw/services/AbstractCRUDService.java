package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.GenericCRUDDao;
import ar.edu.itba.paw.interfaces.GenericCRUDService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class AbstractCRUDService<T> implements GenericCRUDService<T> {

    private final GenericCRUDDao<T> modelDao;

    AbstractCRUDService(GenericCRUDDao<T> modelDao) {
        this.modelDao = modelDao;
    }

    @Override
    public T findById(final long pk) {
        return modelDao.findById(pk);
    }


    @Override
    public List<T> getAll() {
        return modelDao.getAll();
    }

    @Override
    public T create(final T model) {
        return modelDao.create(model);
    }

    @Override
    public void delete(final long pk) {
        modelDao.delete(pk);
    }

}
