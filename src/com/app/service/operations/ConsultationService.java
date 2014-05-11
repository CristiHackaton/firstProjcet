package com.app.service.operations;

import java.io.Serializable;
import java.util.ArrayList;

import com.app.db.gateway.ConsultationGateway;
import com.app.db.model.Consultation;
import com.app.db.model.Model;
import com.app.db.model.Pacient;

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
	public ArrayList<Consultation> getAllConsultationsForPacient(Pacient pacient){
		return consGateway.getConsultationByPacientId(pacient.getId());
	}
	
	public static ConsultationService getInstance() {
		if(ConsultationService.conService == null)
		{
			ConsultationService.conService = new ConsultationService();
		}
		return ConsultationService.conService;
	}

	public void addConsultation(Consultation parameter) {
		consGateway.addConsulation(parameter);
		
	}
	
	public void removeConsultation(Consultation parameter) {
		consGateway.removeConsultation(parameter.getId());
		
	}

	public ArrayList<Consultation> getAllConsultations() {
		return  consGateway.getAllConsultations();
	}
}
