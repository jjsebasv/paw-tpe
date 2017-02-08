package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.interfaces.GenericCRUDDao;
import ar.edu.itba.paw.models.PagedResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

abstract class AbstractCRUDHibernateDao<T> implements GenericCRUDDao<T> {

    private final Class<T> modelType;

    @PersistenceContext
    protected EntityManager em;


    AbstractCRUDHibernateDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    protected Class<T> getModelType() {
        return modelType;
    }

    @Override
    public T findById(final long pk) {
        return em.find(modelType, pk);
    }


    @Override
    public T create(final T modelInstance) {
        em.persist(modelInstance);

        return modelInstance;
    }

    @Override
    public void delete(final long pk) {

        final T modelInstance = this.findById(pk);

        if (modelInstance != null) {
            em.remove(modelInstance);
        }
    }

    @Override
    public List<T> getAll() {
        final TypedQuery<T> query = em.createQuery("from " + modelType.getName(), modelType);
        final List<T> list = query.getResultList();
        return list.isEmpty() ? null : list;
    }

    @Override
    public PagedResult<T> getAll(final int start, final int limit) {
        final TypedQuery<T> query = em.createQuery("from " + modelType.getName(), modelType);

        query.setFirstResult(start);
        query.setMaxResults(limit);

        final List<T> list = query.getResultList();

        return new PagedResult<T>(list.isEmpty() ? null : list, start, limit, count());
    }

    @Override
    public int count() {
        final TypedQuery<Long> countQuery = em.createQuery("select count(*) from " + modelType.getName(), Long.class);
        return countQuery.getSingleResult().intValue();
    }
}

