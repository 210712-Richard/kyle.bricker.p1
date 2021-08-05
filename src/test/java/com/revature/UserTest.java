package com.revature;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.models.User;
import com.revature.services.UserService;

import junit.framework.Assert;

class UserTest {

	@Test
	void testRequest() {
		UserService us = new UserService();
		User kyle=new User();
		us.request(kyle, 800);
		Assert.assertNotNull(kyle.getReimbursements());
	}

//	@Test
//	void testApprove() {
//		fail("Not yet implemented");
//	}

}
