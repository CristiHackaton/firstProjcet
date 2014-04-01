package com.app.service.communication;

import com.app.db.model.Model;


public abstract class OperationService {	
	
	public abstract boolean create(Model model);
	public abstract boolean read(Model model);
	public abstract boolean delete(Model model);
	public abstract boolean update(Model model);
	
}
