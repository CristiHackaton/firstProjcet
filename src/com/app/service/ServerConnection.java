package com.app.service;

import java.util.ArrayList;
import java.util.List;

import com.app.db.model.User;
import com.app.service.communication.CommunicationService;
import com.app.service.operations.ConsultationService;
import com.app.service.operations.AbstractOperationService;
import com.app.service.operations.PacientService;
import com.app.service.operations.UserService;

public class ServerConnection {

	private List<User> acceptedUsers = new ArrayList<User>();
	
	public AbstractOperationService getOperationService(User user) {
		if(user.isAdmin())
			return UserService.getInstance(); 
		if(user.isDoctor())
			return ConsultationService.getInstance(); 
		if(user.isSecretary())
			return PacientService.getInstance(); 
		//impossible
		return null;
	}

}
