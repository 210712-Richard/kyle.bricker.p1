package com.revature.models;

import java.time.LocalDate;

public class Reimbursement{
	private User employee;
	private float amount;
	private LocalDate createdAt;
	private boolean approvedByDS;
	private boolean approvedByHead;
	private boolean approvedByBenCo;
	private boolean exceedingAvailableFunds;
	private boolean approved;
	private boolean denied;
	private String reasonForDenial;
	
	public Reimbursement() {
		this.createdAt = LocalDate.now();
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public boolean isApprovedByDS() {
		return approvedByDS;
	}

	public void setApprovedByDS(boolean approvedByDS) {
		this.approvedByDS = approvedByDS;
	}

	public boolean isApprovedByHead() {
		return approvedByHead;
	}

	public void setApprovedByHead(boolean approvedByHead) {
		this.approvedByHead = approvedByHead;
	}

	public boolean isApprovedByBenCo() {
		return approvedByBenCo;
	}

	public void setApprovedByBenCo(boolean approvedByBenCo) {
		this.approvedByBenCo = approvedByBenCo;
	}

	public boolean isExceedingAvailableFunds() {
		return exceedingAvailableFunds;
	}

	public void setExceedingAvailableFunds(boolean exceedingAvailableFunds) {
		this.exceedingAvailableFunds = exceedingAvailableFunds;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isDenied() {
		return denied;
	}

	public void setDenied(boolean denied) {
		this.denied = denied;
	}

	public String getReasonForDenial() {
		return reasonForDenial;
	}

	public void setReasonForDenial(String reasonForDenial) {
		this.reasonForDenial = reasonForDenial;
	}
	
	
}
