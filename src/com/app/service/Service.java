package com.app.service;

import java.util.ArrayList;
import java.util.List;

import com.app.db.model.User;
import com.app.service.communication.CommunicationService;
import com.app.service.communication.OperationService;

public class Service {

	private final CommunicationService comService = new CommunicationService();
	private final OperationService opService = new OperationService();

	private List<User> acceptedUsers = new ArrayList<User>();

	public CommunicationService getCommunicationService() {
		return this.comService;
	}

	public OperationService getOperationService() {
		return this.opService;
	}

}
