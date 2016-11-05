package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.interfaces.GenericCRUDDao;

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
}
