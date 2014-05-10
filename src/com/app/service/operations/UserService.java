package com.app.service.operations;

import java.util.ArrayList;

import com.app.db.model.Model;
import com.app.db.model.User;

public class UserService extends AbstractOperationService {

	private static UserService userService;

	public UserService() {

	}

//	public boolean create(Model user) {
//		return false;
//	}
//
//	public boolean read(Model user) {
//		return false;
//	}
//
//	public boolean delete(Model user) {
//		return false;
//	}
//
//	public boolean update(Model user) {
//		return false;
//	}
	
	public ArrayList<User> getAllUsers(){
		return userGateway.getAllUsers();
	}
	
	public void addUser(User u){
		userGateway.addUser(u);
	}
	
	public void updateUser(User u) {
		userGateway.updateUser(u);
	}
	
	public void deleteUser(User u){
		userGateway.removeUser(u.getUserID());
	}
	
	public static UserService getInstance() {
		if (UserService.userService == null) {
			UserService.userService = new UserService();
		}
		return UserService.userService;
	}
	

}
