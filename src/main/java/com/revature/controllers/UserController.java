package com.revature.controllers;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.http.Context;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserService us = new UserService();

	public void register(Context ctx) {
		User u = ctx.bodyAsClass(User.class);

		if(us.checkAvailability(u.getName())) {
			u.setId(us.getUsers().size());
			User newUser = us.register(u);
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken.");
		}
		
	}
	
	public void login(Context ctx) {
		log.debug(ctx.body());
		User u = ctx.bodyAsClass(User.class);
		log.debug(u);
		
		u = us.login(u.getName());
		log.debug(u);
		
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
		User u = us.getUser(Long.parseLong(ctx.pathParam("id")));
		ctx.json(u);
	}
	
	public void getUsers(Context ctx) {
		List<User> u = us.getUsers();
		ctx.json(u);
	}
	
	public void promote(Context ctx) {
		User u = us.getUser(Long.parseLong(ctx.pathParam("id")));
		u.setBenCo(true);
		us.updateUser(u);
		ctx.json(u);
	}

}
