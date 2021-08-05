package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.UserController;
import com.revature.models.User;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	public static void main(String[] args) {
		instantiateDatabase();
		javalin();
	}

	public static void instantiateDatabase() {
		DataBaseCreator.dropTables();
		try {
			Thread.sleep(10000); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		DataBaseCreator.createTables();
		try {
			Thread.sleep(10000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void javalin() {
		
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
	
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = new UserController();
	
		app.get("/", (ctx)->ctx.html("Hello World"));		
		app.put("/users", uc::register);
	}
}