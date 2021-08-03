package com.revature.data;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.models.User;
import com.revature.util.CassandraUtil;

public class UserDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	public void addUser(User u) {
		String query = "Insert into users (name) values (?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getName());
		session.execute(bound);
	}
}
