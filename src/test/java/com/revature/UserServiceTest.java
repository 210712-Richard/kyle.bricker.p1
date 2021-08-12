package com.revature;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.revature.services.UserService;
import com.revature.data.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;


class UserServiceTest {
	private static UserService us;
	private static User u;
	
	@BeforeAll
	public static void prepareAll() {
		u=new User();
		u.setName("Test");
		u.setId(UUID.randomUUID());
	}
	
	@BeforeEach
	public void prepareEach() {
		us = new UserService();
		us.ud = Mockito.mock(UserDAO.class);
	}

	@Test
	void testRegister() {
		us.register(u);
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		Mockito.verify(us.ud).addUser(captor.capture());
		User u = captor.getValue();
		assertEquals(1000,u.getAvailableReimbursement());
	}


	@Test
	void testGetUser() {
		assertEquals(null,us.getUser(u.getId()));
	}

	@Test
	void testGetUsers() {
		assertEquals(0,us.getUsers().size());
	}

	@Test
	void testRequest() {
		Reimbursement r = new Reimbursement();
		r.setId(UUID.randomUUID());
		assertNotNull(us.request(u, r));
	}

//	@Test
//	void testGetReimbursements() {
//	}

	@Test
	void testCheckAvailability() {
		assertEquals(true,us.checkAvailability("Test"));
	}
	
	@Test
	void testApprove() {
		assertEquals(null,us.approve(u, UUID.randomUUID()));
	}

	@Test
	void testDeny() {
		assertEquals(null,us.deny(u, UUID.randomUUID(),"TestReason"));
	}

}
