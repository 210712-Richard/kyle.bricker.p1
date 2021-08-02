package com.revature.models;

import java.util.Date;

public class Reimbursement implements ReimbursementInterface {
	private User employee;
	private float amount;
	private Date createdAt;
	private boolean approvedByDS;
	private boolean approvedByHead;
	private boolean approvedByBenCo;
	private boolean exceedingAvailableFunds;
	private boolean approved;
	private boolean denied;
	private String reasonForDenial;
	
	public Reimbursement() {}
	
}
