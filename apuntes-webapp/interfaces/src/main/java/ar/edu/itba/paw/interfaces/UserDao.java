package ar.edu.itba.paw.interfaces;

import ar.edu.itba.paw.models.User;

public interface UserDao {

	User findById(final long id);
	
	User create(String username);

}
