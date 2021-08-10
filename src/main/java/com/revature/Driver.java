package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.User;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	public static void main(String[] args) {
		//instantiateDatabase();
		javalin();
	}

	public static void instantiateDatabase() {
		DataBaseCreator.dropTables();
		try {
			Thread.sleep(50000); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		DataBaseCreator.createTables();
		try {
			Thread.sleep(50000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		DataBaseCreator.populateDatabase();
		System.exit(0);
	}

	public static void javalin() {
		
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
	
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = new UserController();
		ReimbursementController rc = new ReimbursementController();
	
		app.get("/", (ctx)->ctx.html("Hello World"));		
		app.put("/users", uc::register);
		app.get("/users/:id", uc::getUser);
		app.get("/users", uc::getUsers);
		app.get("/users/:id/promote", uc::promote);
		app.post("/users", uc::login);
		app.delete("/users", uc::logout);
		app.post("/reimbursements", uc::request);
		app.get("/reimbursements", rc::getReimbursements);
		app.get("/reimbursements/:id", rc::getReimbursement);
		app.post("/users/:id", uc::approve);
		app.put("/reimbursements/file/:id", rc::upload);
		app.get("/reimbursements/file/:id/:key", rc::getFile);
	}
}