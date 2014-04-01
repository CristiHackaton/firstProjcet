package com.app.service.operations;

import com.app.db.model.Model;

public class PacientService extends AbstractOperationService {

	private static PacientService patService;
	
	private PacientService(){
		
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
		if(PacientService.patService == null)
		{
			PacientService.patService = new PacientService();
		}
		return PacientService.patService;
	}
}
