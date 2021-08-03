package com.revature.services;

import com.revature.data.UserDAO;
import com.revature.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
	private Logger log = LogManager.getLogger(UserService.class);
	public UserDAO ud = new UserDAO();
	
	public User register(String name) {
		User u = new User();
		u.setName(name);
		ud.addUser(u);
		return u;
	}

	public boolean checkAvailability(String name) {
		// TODO implement
		return true;
	}
}
