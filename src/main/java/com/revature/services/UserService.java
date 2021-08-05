package com.revature.services;

import com.revature.data.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;

import java.util.List;

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
	
	public Reimbursement request(User u, float amount) {
		Reimbursement r = new Reimbursement();
		r.setAmount(amount);
		List<Reimbursement> l = u.getReimbursements();
		l.add(r);
		u.setReimbursements(l);;
		return r;
	}
	
	public Reimbursement approve(User u, Reimbursement r) {
		if (r.getEmployee().getSupervisor().equals(this)) {
			r.setApprovedByDS(true);
		}
		if (r.getEmployee().getHead().equals(this)) {
			r.setApprovedByHead(true);
		}
		if (u.isBenCo()) {
			r.setApprovedByBenCo(true);
		}
		if (r.isApprovedByDS() && r.isApprovedByHead() && r.isApprovedByBenCo()) {
			r.setApproved(true);
		}
		return r;
	}

	public boolean checkAvailability(String name) {
		// TODO implement
		return true;
	}
}
