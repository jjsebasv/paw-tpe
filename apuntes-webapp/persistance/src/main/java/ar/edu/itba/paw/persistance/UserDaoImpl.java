package ar.edu.itba.paw.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.models.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private	UserDao	userDao;

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
