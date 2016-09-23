package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.ClientDao;
import ar.edu.itba.paw.interfaces.ClientService;
import ar.edu.itba.paw.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao userDao;

    @Autowired
    public ClientServiceImpl(ClientDao userDao) {
        this.userDao = userDao;
    }

    public Client findById(int id) {
        return userDao.findById(id);
    }

    public int create(String username, String password) {
        return userDao.create(username, password).getUserid();
    }


}
