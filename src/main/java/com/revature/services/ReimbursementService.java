package com.revature.services;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.data.ReimbursementDAO;
import com.revature.data.UserDAO;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	private Logger log = LogManager.getLogger(UserService.class);
	public UserDAO ud = new UserDAO();
	public ReimbursementDAO rd = new ReimbursementDAO();
	
	public Reimbursement getReimbursement(UUID id) {
		return rd.getById(id);
	}

	public List<Reimbursement> getReimbursements() {
		return rd.getReimbursements();
	}
	
	public void updateReimbursement(Reimbursement r) {
		rd.updateReimbursement(r);
	}
	
}
