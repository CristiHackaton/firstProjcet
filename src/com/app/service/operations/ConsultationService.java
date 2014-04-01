package com.app.service.operations;

import com.app.db.model.Model;

public class ConsultationService extends AbstractOperationService {

	private static ConsultationService conService;
	
	private ConsultationService(){
		
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
		if(ConsultationService.conService == null)
		{
			ConsultationService.conService = new ConsultationService();
		}
		return ConsultationService.conService;
	}
}
