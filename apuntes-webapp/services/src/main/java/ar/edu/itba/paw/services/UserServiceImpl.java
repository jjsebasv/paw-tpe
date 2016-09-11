package ar.edu.itba.paw.services;

import org.springframework.stereotype.Service;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long create(String username) {
		// TODO Auto-generated method stub
		return 1;
	}
	
}
