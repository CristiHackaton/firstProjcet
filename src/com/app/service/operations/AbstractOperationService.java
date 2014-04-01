package com.app.service.operations;

import com.app.db.gateway.UserGateway;
import com.app.db.model.Model;
import com.app.db.model.User;


public abstract class AbstractOperationService {	
	
	//use this for implementing Services
	protected static UserGateway userGateway = new UserGateway();
	
	public abstract boolean create(Model model);
	public abstract boolean read(Model model);
	public abstract boolean delete(Model model);
	public abstract boolean update(Model model);

	public static User Login(Model user) {
		User existingUser = null;
		if (user instanceof User) {
			User prettendingUser = (User) user;
			existingUser = userGateway.getUserByNameAndPassword(
					prettendingUser.getUsername(),
					prettendingUser.getPassword());
		}
		return existingUser;
	}
}
