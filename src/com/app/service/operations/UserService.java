package com.app.service.operations;

import com.app.db.model.Model;
import com.app.db.model.User;

public class UserService extends AbstractOperationService {

	private static UserService userService;

	public UserService() {

	}

	public boolean create(Model user) {
		return false;
	}

	public boolean read(Model user) {
		return false;
	}

	public boolean delete(Model user) {
		return false;
	}

	public boolean update(Model user) {
		return false;
	}
	
	public static AbstractOperationService getInstance() {
		if (UserService.userService == null) {
			UserService.userService = new UserService();
		}
		return UserService.userService;
	}
	

}
