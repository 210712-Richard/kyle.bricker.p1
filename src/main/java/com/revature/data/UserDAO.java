package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.models.User;
import com.revature.util.CassandraUtil;
import java.util.stream.Collectors;


public class UserDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	public void addUser(User u) {
		System.out.println(u);
		String query = "Insert into users (id, availableReimbursement, name, supervisorId, headID, benCo) values (?,?,?,?,?,?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getId(),u.getAvailableReimbursement(),u.getName(),u.getSupervisorId(),u.getHeadId(),u.isBenCo());
		session.execute(bound);
	}
	
	public User getUser(UUID uuid) {
		String query = "Select * from users where id=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(uuid);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		User u = new User();
		u.setId(row.getUuid("id"));
		u.setName(row.getString("name"));
		u.setAvailableReimbursement(row.getLong("availableReimbursement"));
		u.setSupervisorId(row.getUuid("supervisorId"));
		u.setHeadId(row.getUuid("headId"));
		u.setBenCo(row.getBool("benCo"));
		return u;
	}
	
	public User getUser(String name) {
		String query = "Select * from users where name=? allow filtering";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(name);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		User u = new User();
		u.setId(row.getUuid("id"));
		u.setName(row.getString("name"));
		u.setAvailableReimbursement(row.getLong("availableReimbursement"));
		u.setSupervisorId(row.getUuid("supervisorId"));
		u.setHeadId(row.getUuid("headId"));
		u.setBenCo(row.getBool("benCo"));
		return u;
	}
	
	public List<User> getUsers() {
		String query = "Select * from users";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<User> users = new ArrayList<>();
		rs.forEach(row -> {
			User u = new User();
			u.setId(row.getUuid("id"));
			u.setName(row.getString("name"));
			u.setAvailableReimbursement(row.getLong("availableReimbursement"));
			u.setSupervisorId(row.getUuid("supervisorId"));
			u.setHeadId(row.getUuid("headId"));
			u.setBenCo(row.getBool("benCo"));
			users.add(u);
		});
		return users;
	}
	
	public void updateUser(User u) {
		String query = "Update users set name = ?, availableReimbursement = ?, supervisorId = ?, headId = ?, benCo = ?, reimbursements = ? where id = ?;";
		List<UUID> reimbursements = u.getReimbursements()
				.stream()
				.filter(r -> r!=null)
				.map(r -> r.getId())
				.collect(Collectors.toList());
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getName(), u.getAvailableReimbursement(), u.getSupervisorId(), u.getHeadId(), u.isBenCo(), reimbursements, u.getId());
		session.execute(bound);
	}
	
	public List<UUID> getReimbursements(UUID id) {
		String query = "Select reimbursements from users where id=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(id);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		List<UUID> reimbursements = row.getList("reimbursements", UUID.class);
		return reimbursements;
	}
}
