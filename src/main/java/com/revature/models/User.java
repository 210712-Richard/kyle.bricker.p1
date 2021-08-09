package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Serializable{
	private UUID id;
	private long availableReimbursement=1000l;
	private String name;
	private UUID supervisorId;
	private UUID headId;
	private boolean benCo;
	private List<Reimbursement> reimbursements = new ArrayList<Reimbursement>(0);
	private static final long serialVersionUID = 2622951313170302027L;
	
	public User() {}
	

	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public long getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(long availableReimbursement) {
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
	public UUID getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(UUID supervisorId) {
		this.supervisorId = supervisorId;
	}

	public UUID getHeadId() {
		return headId;
	}

	public void setHeadId(UUID headId) {
		this.headId = headId;
	}

	public boolean isBenCo() {
		return benCo;
	}

	public void setBenCo(boolean benCo) {
		this.benCo = benCo;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", availableReimbursement=" + availableReimbursement + ", name=" + name
				+ ", supervisorId=" + supervisorId + ", headId=" + headId + ", benCo=" + benCo + ", reimbursements="
				+ reimbursements + "]";
	}
	
}
