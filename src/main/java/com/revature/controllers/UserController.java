package com.revature.controllers;

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
			User newUser = us.register(u.getName());
			ctx.status(201);
			ctx.json(newUser);
		} else {
			ctx.status(409);
			ctx.html("Username already taken.");
		}
		
	}

}
