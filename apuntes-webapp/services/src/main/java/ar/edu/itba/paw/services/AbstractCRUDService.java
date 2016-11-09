package ar.edu.itba.paw.services;


import ar.edu.itba.paw.interfaces.GenericCRUDDao;
import ar.edu.itba.paw.interfaces.GenericCRUDService;
import ar.edu.itba.paw.models.PagedResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class AbstractCRUDService<T> implements GenericCRUDService<T> {

    private final GenericCRUDDao<T> modelDao;

    private static final int RESULTS_PER_PAGE = 20;

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
    public PagedResult<T> getAll(int page) {

        final int count = modelDao.count();

        if (page < 0 || count < ((page - 1) * RESULTS_PER_PAGE)) {
            return null;
        }

        final int start = (page - 1) * RESULTS_PER_PAGE;//0-indexed

        return modelDao.getAll(start, RESULTS_PER_PAGE);
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
