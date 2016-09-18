package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

public interface UserService {

    User findById(long id);

    long create(String username, String password);
}
