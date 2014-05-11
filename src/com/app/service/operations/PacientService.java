package com.app.service.operations;

import java.util.ArrayList;
import java.util.List;

import com.app.db.gateway.PacientGateway;
import com.app.db.model.Model;
import com.app.db.model.Pacient;
import com.app.db.model.User;

public class PacientService extends AbstractOperationService {

	private static PacientService patService;
	private static PacientGateway pacientGateway;
	
	private PacientService(){
		pacientGateway = new PacientGateway();
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
	
	public ArrayList<Pacient> getAllPatitens(){
		return pacientGateway.getAllPacients();
	}
	
	public static PacientService getInstance() {
		if(PacientService.patService == null)
		{
			PacientService.patService = new PacientService();
		}
		return PacientService.patService;
	}

	public ArrayList<Pacient> getPatientsForDoctor(User user) {
		return pacientGateway.getPatientsForDoctor(user.getUserID());
		
	}
}
