package com.revature;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.UserService;

import junit.framework.Assert;

class UserTest {

	@Test
	void testRequest() {
		UserService us = new UserService();
		User kyle=new User();
		Reimbursement r = new Reimbursement();
		us.request(kyle, r);
		Assert.assertNotNull(kyle.getReimbursements());
	}

//	@Test
//	void testApprove() {
//		fail("Not yet implemented");
//	}

}
