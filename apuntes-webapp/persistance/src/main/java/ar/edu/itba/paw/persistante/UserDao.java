package ar.edu.itba.paw.persistante;


public interface UserDao {

	User findById(final long id);
	
	User create(String username);

}
