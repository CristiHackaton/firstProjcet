package com.app.service;

import java.util.ArrayList;
import java.util.List;

import com.app.db.model.User;
import com.app.service.communication.CommunicationService;
import com.app.service.communication.OperationService;

public class ServerConnection {

	private final CommunicationService comService = new CommunicationService();
	private final OperationService opService = new OperationService();

	private List<User> acceptedUsers = new ArrayList<User>();

	public OperationService getOperationService(User user) {
		if(user.is)
		return this.opService;
	}

}
