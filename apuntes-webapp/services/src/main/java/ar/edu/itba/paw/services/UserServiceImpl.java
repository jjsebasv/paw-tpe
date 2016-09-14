package ar.edu.itba.paw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.UserDao;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public long create(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.create(username, password).getId();
	}
	
	
}
