package com.revature;

import java.util.UUID;

import com.revature.data.UserDAO;
import com.revature.models.User;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAO();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS users;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		sb = new StringBuilder("DROP TABLE IF EXISTS reimbursements;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
	}
	
	public static void createTables() {
		StringBuilder sb = new StringBuilder(
				"CREATE TABLE IF NOT EXISTS users (id uuid PRIMARY KEY, availableReimbursement bigint, name text, supervisorId uuid, headID uuid, benCo boolean, reimbursements list<uuid> );"
				);
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		sb = new StringBuilder(
				"CREATE TABLE IF NOT EXISTS reimbursements (id uuid PRIMARY KEY, creatorId uuid, amount bigint, createdAt date, approvedByDS boolean, approvedByHead boolean, approvedByBenCo boolean, exceedingAvailableFunds boolean, approved boolean, denied boolean, reasonForDenial text, fileUrl text);"
				);
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}
	public static void populateDatabase() {
		User u1 = new User();
		u1.setName("CEO");
		u1.setId(UUID.randomUUID());
		ud.addUser(u1);
		User u2 = new User();
		u2.setName("Head");
		u2.setId(UUID.randomUUID());
		u2.setHeadId(u2.getId());
		u2.setSupervisorId(u1.getId());
		ud.addUser(u2); 
		User u3 = new User();
		u3.setName("Supervisor");
		u3.setId(UUID.randomUUID());
		u3.setHeadId(u2.getId());
		u3.setSupervisorId(u2.getId());
		ud.addUser(u3); 
		User u4 = new User();
		u4.setName("BenCo");
		u4.setId(UUID.randomUUID());
		u4.setHeadId(u2.getId());
		u4.setSupervisorId(u1.getId());
		u4.setBenCo(true);
		ud.addUser(u4); 
		User u5 = new User();
		u5.setName("Employee");
		u5.setId(UUID.randomUUID());
		u5.setHeadId(u2.getId());
		u5.setSupervisorId(u3.getId());
		ud.addUser(u5); 
	}
	
}