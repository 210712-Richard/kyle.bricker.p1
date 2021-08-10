package com.revature.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Context;
import jdk.internal.org.jline.utils.Log;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();

	public void register(Context ctx) {
		User u = ctx.bodyAsClass(User.class);

		if(us.checkAvailability(u.getName())) {
			u.setId(UUID.randomUUID());
			User newUser = us.register(u);
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken.");
		}
		
	}
	
	public void request(Context ctx) {
		Reimbursement r = ctx.bodyAsClass(Reimbursement.class);
		User u = ctx.sessionAttribute("loggedUser");
		if(u==null) {
			ctx.status(403);
		} else {
			r.setId(UUID.randomUUID());
			r.setCreatedAt(LocalDate.now());
			r.setCreatorId(u.getId());
			log.debug(r.toString());
			r = us.request(ctx.sessionAttribute("loggedUser"), r);
			ctx.json(r);
		}
	}
	
	public void login(Context ctx) {
		User u = ctx.bodyAsClass(User.class);		
		u = us.login(u.getName());		
		if(u != null) {
			ctx.sessionAttribute("loggedUser", u);
			ctx.json(u);
			return;
		}
		ctx.status(401);
	}
	
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}
	
	public void getUser(Context ctx) {
		User u = us.getUser(UUID.fromString(ctx.pathParam("id")));
		ctx.json(u);
	}
	
	public void getUsers(Context ctx) {
		List<User> u = us.getUsers();
		ctx.json(u);
	}
	
	public void promote(Context ctx) {
		User u = us.getUser(UUID.fromString(ctx.pathParam("id")));
		u.setBenCo(true);
		us.updateUser(u);
		ctx.json(u);
	}
	
	public void approve(Context ctx) {
		User u = ctx.sessionAttribute("loggedUser");
		if(u==null) {
			ctx.status(403);
		} else {
			us.approve(u,UUID.fromString(ctx.pathParam("id")));
		}
	}

}
