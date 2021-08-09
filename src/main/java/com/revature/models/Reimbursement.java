package com.revature.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Reimbursement implements Serializable{
	private UUID id;
	private UUID creatorId;
	private long amount;
	private LocalDate createdAt;
	private boolean approvedByDS;
	private boolean approvedByHead;
	private boolean approvedByBenCo;
	private boolean exceedingAvailableFunds;
	private boolean approved;
	private boolean denied;
	private String reasonForDenial;
	private String fileUrl;
	private static final long serialVersionUID = 2622951313170302027L;

	
	public Reimbursement() {
		this.createdAt = LocalDate.now();
	}

	public UUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(UUID creatorId) {
		this.creatorId = creatorId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	
}
