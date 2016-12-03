package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.UniversityDao;
import ar.edu.itba.paw.models.University;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityHibernateDao extends AbstractCRUDHibernateDao<University> implements UniversityDao {
    UniversityHibernateDao() {
        super(University.class);
    }
}
