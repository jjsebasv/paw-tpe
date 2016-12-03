package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.UniversityDao;
import ar.edu.itba.paw.interfaces.UniversityService;
import ar.edu.itba.paw.models.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UniversityServiceImpl extends AbstractCRUDService<University> implements UniversityService {

    private final UniversityDao universityDao;

    @Autowired
    public UniversityServiceImpl(UniversityDao universityDao) {
        super(universityDao);
        this.universityDao = universityDao;
    }

    @Override
    public void update(long pk, University from) {
        final University instance = findById(pk);

        instance.setName(from.getName());
    }
}
