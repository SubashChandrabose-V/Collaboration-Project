package org.com.service;

import org.com.dao.UserDAO;
import org.com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public boolean registerUser(User user) {
		
		return userDAO.registerUser(user);
	}

	public boolean isUsernameValid(String username) {
	
		return userDAO.isUsernameValid(username);
	}

	public boolean isEmailValid(String email) {

		return userDAO.isEmailValid(email);
	}

	@Override
	public User login(User user) {
		return userDAO.login(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
		
	}

	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	@Override
	public boolean isUpdatedEmailValid(String email, String username) {
		return userDAO.isUpdatedEmailValid(email, username);
	}
	
	


}
