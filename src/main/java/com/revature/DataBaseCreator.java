package com.revature;

import com.revature.data.UserDAO;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAO();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS users;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
	}
	
	public static void createTables() {
		StringBuilder sb = new StringBuilder(
				"CREATE TABLE IF NOT EXISTS users (id bigint PRIMARY KEY, availableReimbursement bigint, name text, supervisorId bigint, headID bigint, benCo boolean, reimbursements list<uuid> );"
				);
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}
	
//	public static void populateUserTable() {
//		User u = new User("Richard","richard.orr@revature.com", LocalDate.of(1960, 8, 30), 2000l);
//		u.setType(UserType.GAME_MASTER);
//		ud.addUser(u);
//		ud.addUser(new User("Michael", "michael@michael.michael", LocalDate.of(1700, 5, 6), 1000l));
//		ud.addUser(new User("Jaclyn", "jaclyn@jaclyn.jaclyn", LocalDate.of(1660, 4, 2), 1000l));
//		ud.addUser(new User("Joshua", "one@josh.alltime", LocalDate.of(1984, 1, 25), 1000l));
//		ud.addUser(new User("Stephen", "stephen@steven.steve", LocalDate.of(1880, 7, 23), 1000l));
//	}
}