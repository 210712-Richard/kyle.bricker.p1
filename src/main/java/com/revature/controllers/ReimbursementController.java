package com.revature.controllers;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.util.S3Util;

import io.javalin.http.Context;

public class ReimbursementController {
	
	private ReimbursementService rs = new ReimbursementService();
	
	public void getReimbursement(Context ctx) {
		Reimbursement r = rs.getReimbursement(UUID.fromString(ctx.pathParam("id")));
		ctx.json(r);
	}
	
	public void getReimbursements(Context ctx) {
		List<Reimbursement> r = rs.getReimbursements();
		ctx.json(r);
	}
	
	public void upload(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		UUID id = UUID.fromString(ctx.pathParam("id"));
		Reimbursement r = rs.getReimbursement(id);
		if(r == null) {
			ctx.status(404);
			return;
		}
		String filename = ctx.header("filename");
		if(filename == null) {
			ctx.status(400);
			return;
		}
		if (filename.toLowerCase().contains("supervisor")) {
			r.setApprovedByDS(true);
		}
		if (filename.toLowerCase().contains("head")) {
			r.setApprovedByHead(true);
		}
		String key = filename;
		S3Util.getInstance().uploadToBucket(key, ctx.bodyAsBytes());
		List<String> files = r.getFiles();
		files.add(key);
		r.setFiles(files);
		rs.updateReimbursement(r);;
		ctx.json(r);
	}
	
	public void getFile(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		UUID id = UUID.fromString(ctx.pathParam("id"));
		Reimbursement r = rs.getReimbursement(id);
		if(r == null) {
			ctx.status(404);
			return;
		}
		try {
			InputStream file = S3Util.getInstance().getObject(ctx.pathParam("key"));
			ctx.result(file);
		} catch (Exception e) {
			ctx.status(500);
		}
	}

}
