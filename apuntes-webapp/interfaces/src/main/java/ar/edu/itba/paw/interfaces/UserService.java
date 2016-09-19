package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

public interface UserService {

    User findById(int id);

    int create(String username, String password);
}
