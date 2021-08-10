package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.CassandraUtil;

public class ReimbursementDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	public void addReimbursement(Reimbursement r) {
		String query = "Insert into reimbursements (id, creatorId, amount, createdAt, eventDate, eventLocation, gradeFormatIsPNP, approvedByDS, approvedByHead, approvedByBenCo, exceedingAvailableFunds, approved, denied, reasonForDenial, files) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(r.getId(),r.getCreatorId(),r.getAmount(),r.getCreatedAt(),r.getEventDate(),r.getEventLocation(),r.getGradeFormatIsPNP(),r.isApprovedByDS(),r.isApprovedByHead(),r.isApprovedByBenCo(),r.isExceedingAvailableFunds(),r.isApproved(),r.isDenied(),r.getReasonForDenial(),r.getFiles());
		session.execute(bound);
	}


	public Reimbursement getById(UUID id) {
		String query = "Select * from reimbursements where id=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(id);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		Reimbursement r = new Reimbursement();
		r.setId(row.getUuid("id"));
		r.setCreatorId(row.getUuid("creatorId"));
		r.setAmount(row.getLong("amount"));
		r.setCreatedAt(row.getLocalDate("createdAt"));
		r.setEventDate(row.getLocalDate("eventDate"));
		r.setEventLocation(row.getString("eventLocation"));
		r.setGradeFormatIsPNP(row.getBool("gradeFormatIsPNP"));
		r.setApprovedByDS(row.getBool("approvedByDS"));
		r.setApprovedByHead(row.getBool("approvedByHead"));
		r.setApprovedByBenCo(row.getBool("approvedByBenCo"));
		r.setExceedingAvailableFunds(row.getBool("exceedingAvailableFunds"));
		r.setApproved(row.getBool("approved"));
		r.setDenied(row.getBool("denied"));
		r.setReasonForDenial(row.getString("reasonForDenial"));
		r.setFiles(row.getList("files", String.class));
		return r;
	}
	
	public List<Reimbursement> getReimbursements() {
		String query = "Select * from reimbursements";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<Reimbursement> reimbursements = new ArrayList<>();
		rs.forEach(row -> {
			Reimbursement r = new Reimbursement();
			r.setId(row.getUuid("id"));
			r.setCreatorId(row.getUuid("creatorId"));
			r.setAmount(row.getLong("amount"));
			r.setCreatedAt(row.getLocalDate("createdAt"));
			r.setEventDate(row.getLocalDate("eventDate"));
			r.setEventLocation(row.getString("eventLocation"));
			r.setGradeFormatIsPNP(row.getBool("gradeFormatIsPNP"));
			r.setApprovedByDS(row.getBool("approvedByDS"));
			r.setApprovedByHead(row.getBool("approvedByHead"));
			r.setApprovedByBenCo(row.getBool("approvedByBenCo"));
			r.setExceedingAvailableFunds(row.getBool("exceedingAvailableFunds"));
			r.setApproved(row.getBool("approved"));
			r.setDenied(row.getBool("denied"));
			r.setReasonForDenial(row.getString("reasonForDenial"));
			r.setFiles(row.getList("files", String.class));
			reimbursements.add(r);
		});
				
		return reimbursements;
	}
	
	public void updateReimbursement(Reimbursement r) {
		String query = "Update reimbursements set creatorId = ?, amount = ?, createdAt = ?, eventDate = ?, eventLocation = ?, gradeFormatIsPNP = ?, approvedByDS = ?, approvedByHead = ?, approvedByBenCo = ?, exceedingAvailableFunds=?, approved = ?, denied = ?, reasonForDenial = ?, files = ? where id = ?;";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(r.getCreatorId(),r.getAmount(),r.getCreatedAt(),r.getEventDate(),r.getEventLocation(),r.getGradeFormatIsPNP(),r.isApprovedByDS(),r.isApprovedByHead(),r.isApprovedByBenCo(),r.isExceedingAvailableFunds(),r.isApproved(),r.isDenied(),r.getReasonForDenial(),r.getFiles(),r.getId());
		session.execute(bound);
	}
}
