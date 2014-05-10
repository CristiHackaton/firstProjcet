package com.app.service.operations;

import com.app.db.gateway.ConsultationGateway;
import com.app.db.model.Consultation;
import com.app.db.model.Model;

public class ConsultationService extends AbstractOperationService {

	private static ConsultationService conService;
	private ConsultationGateway consGateway;
	
	private ConsultationService(){
		consGateway = new ConsultationGateway();
		
	}
	
//	public boolean create(Model user) {
//		return false;
//	}
//
//	public boolean read(Model user) {
//		return false;
//	}
//
//	public boolean delete(Model user) {
//		return false;
//	}
//
//	public boolean update(Model user) {
//		return false;
//	}

	public void updateConsultation(Consultation consultation){
		consGateway.updateConsultation(consultation);
	}
	public static ConsultationService getInstance() {
		if(ConsultationService.conService == null)
		{
			ConsultationService.conService = new ConsultationService();
		}
		return ConsultationService.conService;
	}
}
