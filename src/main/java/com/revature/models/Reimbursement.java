package com.revature.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Reimbursement implements Serializable{
	private UUID id;
	private UUID creatorId;
	private long amount;
	private LocalDate createdAt;
	private LocalDate eventDate;
	private String eventLocation;
	private boolean gradeFormatIsPNP;
	private boolean approvedByDS;
	private boolean approvedByHead;
	private boolean approvedByBenCo;
	private boolean exceedingAvailableFunds;
	private boolean approved;
	private boolean denied;
	private String reasonForDenial;
	private List<String> files;
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

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public boolean getGradeFormatIsPNP() {
		return gradeFormatIsPNP;
	}

	public void setGradeFormatIsPNP(boolean gradeFormatIsPNP) {
		this.gradeFormatIsPNP = gradeFormatIsPNP;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", creatorId=" + creatorId + ", amount=" + amount + ", createdAt="
				+ createdAt + ", eventDate=" + eventDate + ", eventLocation=" + eventLocation + ", gradeFormatIsPNP="
				+ gradeFormatIsPNP + ", approvedByDS=" + approvedByDS + ", approvedByHead=" + approvedByHead
				+ ", approvedByBenCo=" + approvedByBenCo + ", exceedingAvailableFunds=" + exceedingAvailableFunds
				+ ", approved=" + approved + ", denied=" + denied + ", reasonForDenial=" + reasonForDenial + ", files="
				+ files + "]";
	}


	
}
