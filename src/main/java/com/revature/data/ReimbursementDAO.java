package com.revature.data;

import java.util.UUID;

import com.datastax.oss.driver.api.core.CqlSession;
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
		r.setAmount(row.getLong("amount"));
		r.setCreatedAt(row.getLocalDate("createdAt"));
		r.setApprovedByDS(row.getBool("approvedByDS"));
		r.setApprovedByHead(row.getBool("approvedByHead"));
		r.setApprovedByBenCo(row.getBool("approvedByBenCo"));
		r.setExceedingAvailableFunds(row.getBool("exceedingAvailableFunds"));
		r.setApproved(row.getBool("approved"));
		r.setDenied(row.getBool("denied"));
		r.setReasonForDenial(row.getString("reasonForDenial"));
		
		return r;
	}
}
