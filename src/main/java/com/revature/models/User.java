package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	private long id;
	private float availableReimbursement=1000;
	private String name;
	private User supervisor;
	private User head;
	private boolean benCo;
	private List<Reimbursement> reimbursements = new ArrayList<Reimbursement>(0);
	
	public User() {}

	public float getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(float availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

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
	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}

	public User getHead() {
		return head;
	}

	public void setHead(User head) {
		this.head = head;
	}

	public boolean isBenCo() {
		return benCo;
	}

	public void setBenCo(boolean benCo) {
		this.benCo = benCo;
	}
}
