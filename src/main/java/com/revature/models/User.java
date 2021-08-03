package com.revature.models;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	private String name;
	private List<Reimbursement> reimbursements;
	
	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

}
