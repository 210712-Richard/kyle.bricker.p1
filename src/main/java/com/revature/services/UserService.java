package com.revature.services;

import com.revature.data.UserDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import java.util.stream.Collectors;


import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
	private Logger log = LogManager.getLogger(UserService.class);
	public UserDAO ud = new UserDAO();
	public ReimbursementDAO rd = new ReimbursementDAO();
	
	public User register(User u) {
		ud.addUser(u);
		return u;
	}
	
	public User login(String name) {
		User u = ud.getUser(name);
		List<UUID> reimbursementIds = ud.getReimbursements(u.getId());
		
		List<Reimbursement> reimbursements = reimbursementIds.stream()
				.map(id -> rd.getById(id))
				.collect(Collectors.toList());
		u.setReimbursements(reimbursements);
		return u;
	}
	
	public User getUser(long id) {
		return ud.getUser(id);
	}
	
	public List<User> getUsers(){
		return ud.getUsers();
	}
	
	public Reimbursement request(User u, long amount) {
		Reimbursement r = new Reimbursement();
		r.setAmount(amount);
		List<Reimbursement> l = u.getReimbursements();
		l.add(r);
		u.setReimbursements(l);;
		return r;
	}

	public boolean checkAvailability(String name) {
		if (ud.getUser(name)==null) {
			return true;
		}
		return false;
	}
	
	public void updateUser(User u) {
		ud.updateUser(u);
	}
}
