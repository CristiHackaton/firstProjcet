package com.app.service;

import java.util.ArrayList;
import java.util.List;

import com.app.db.model.User;
import com.app.service.operations.AbstractOperationService;
import com.app.service.operations.ConsultationService;
import com.app.service.operations.PacientService;
import com.app.service.operations.UserService;

public class ServerConnection {
	
	public AbstractOperationService getOperationService(User user) {
		//login user
		User existingUser = AbstractOperationService.Login(user);
		//select associated service
		if(existingUser.isAdmin())
			return UserService.getInstance(); 
		if(existingUser.isDoctor())
			return ConsultationService.getInstance(); 
		if(existingUser.isSecretary())
			return PacientService.getInstance(); 
		//impossible
		return null;
	}

}
