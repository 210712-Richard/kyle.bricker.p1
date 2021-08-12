package com.revature.services;

import com.revature.data.UserDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
	private Logger log = LogManager.getLogger(UserService.class);
	public UserDAO ud = new UserDAO();
	public ReimbursementDAO rd = new ReimbursementDAO();
	
	public User register(User u) {
		ud.addUser(u);
		return u;
	}
	
	public User login(String name) {
		User u = ud.getUser(name);
		List<UUID> reimbursementIds = ud.getReimbursements(u.getId());
		
		List<Reimbursement> reimbursements = reimbursementIds.stream()
				.map(id -> rd.getById(id))
				.collect(Collectors.toList());
		u.setReimbursements(reimbursements);
		return u;
	}
	
	public User getUser(UUID userId) {
		User u = ud.getUser(userId);
		if (u==null) {return null;}
		List<UUID> reimbursementIds = ud.getReimbursements(u.getId());
		List<Reimbursement> reimbursements = reimbursementIds.stream()
				.map(id -> rd.getById(id))
				.collect(Collectors.toList());
		u.setReimbursements(reimbursements);
		return u;
	}
	
	public List<User> getUsers(){
		List<User> u = ud.getUsers();
		u.forEach(user -> {
			List<UUID> reimbursementIds = ud.getReimbursements(user.getId());
			List<Reimbursement> reimbursements = reimbursementIds.stream()
					.map(id -> rd.getById(id))
					.collect(Collectors.toList());
			user.setReimbursements(reimbursements);
		});
		return u;
	}
	
	public Reimbursement request(User u, Reimbursement r) {
		List<Reimbursement> l = u.getReimbursements();
		l.add(r);
		u.setReimbursements(l);
		u.setAvailableReimbursement(u.getAvailableReimbursement()-r.getAmount());
		System.out.println(l);
		ud.updateUser(u);
		rd.addReimbursement(r);
		return r;
	}
//	
//	public List<Reimbursement> getReimbursements(User u){
//		return null;
//		
//	}

	public boolean checkAvailability(String name) {
		if (ud.getUser(name)==null) {
			return true;
		}
		return false;
	}
	
	public void updateUser(User u) {
		ud.updateUser(u);
	}

	public Reimbursement approve(User u, UUID id) {
		Reimbursement request = rd.getById(id);
		if(request==null) {
			return null;
		}
		if(request.isDenied()) {
			return null;
		}
		User requestor = ud.getUser(request.getCreatorId());
		if (request.getCreatedAt().isBefore(LocalDate.now().minusWeeks(1))) {
			request.setApprovedByDS(true);
			request.setApprovedByHead(true);
		}
		if (u.getId().equals(requestor.getSupervisorId())) {
			request.setApprovedByDS(true);
		}
		if (u.getId().equals(requestor.getHeadId())) {
			request.setApprovedByHead(true);
		}
		if (u.isBenCo()) {
			request.setApprovedByBenCo(true);
		}
		if (u.getId().equals(requestor.getSupervisorId()) && 
				request.getGradeFormatIsPNP() && request.getFiles().size()>1 &&
				request.isApprovedByBenCo() && request.isApprovedByHead()) {
			request.setApproved(true);
		}
		if (u.isBenCo() && 
				!request.getGradeFormatIsPNP() && request.getFiles().size()>1 &&
				request.isApprovedByDS() && request.isApprovedByHead()) {
			request.setApproved(true);
		}
		rd.updateReimbursement(request);
		return request;
	}
	
	public Reimbursement deny(User u, UUID id, String reason) {
		Reimbursement r = rd.getById(id);
		if (r==null) {return null;}
		if (u.isBenCo()) {
			r.setDenied(true);
			r.setReasonForDenial(reason);
			rd.updateReimbursement(r);
			return r;
		}
		return null;
	}
}
