package com.app.service.operations;

import com.app.db.gateway.UserGateway;
import com.app.db.model.Model;


public abstract class AbstractOperationService {	
	
	//use this for implementing Services
	protected UserGateway userGateway = new UserGateway();
	
	public abstract boolean create(Model model);
	public abstract boolean read(Model model);
	public abstract boolean delete(Model model);
	public abstract boolean update(Model model);

}
