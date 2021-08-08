package com.revature;

import com.revature.data.UserDAO;
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
				"CREATE TABLE IF NOT EXISTS users (id bigint PRIMARY KEY, availableReimbursement bigint, name text, supervisorId uuid, headID uuid, benCo boolean, reimbursements list<uuid> );"
				);
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		sb = new StringBuilder(
				"CREATE TABLE IF NOT EXISTS reimbursements (id uuid PRIMARY KEY, amount bigint, createdAt date, approvedByDS boolean, approvedByHead boolean, approvedByBenCo boolean, exceedingAvailableFunds boolean, approved boolean, denied boolean, reasonForDenial text);"
				);
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}
	
}