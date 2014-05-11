package com.app.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.app.db.model.Consultation;
import com.app.db.model.Pacient;
import com.app.db.model.RequestType;
import com.app.db.model.SocketRequest;
import com.app.db.model.User;
import com.app.service.operations.AbstractOperationService;
import com.app.service.operations.ConsultationService;
import com.app.service.operations.PacientService;
import com.app.service.operations.UserService;

public class ServerConnection {

	private static ServerSocket listener;
	private static PrintWriter out;

	public static void startServer() throws IOException {
		ServerSocket listener = new ServerSocket(6969);
		try {

			while (true) {
				Socket socket = listener.accept();
				try {
					ObjectInputStream in = new ObjectInputStream(
							socket.getInputStream());
					try {
						SocketRequest sock = (SocketRequest) in.readObject();
//						System.out.println(sock.getUser().getUsername());
						if (sock.isNeedsResponse()) {
							System.out.println("needs server response");

							ObjectOutputStream out = new ObjectOutputStream(
									socket.getOutputStream());

							SocketRequest response = parseRequest(sock);
							out.writeObject(response);
							out.flush();
						} else {
							parseRequest(sock);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}

	private static SocketRequest parseRequest(SocketRequest sock) {
		System.out.println(">>>>>>>> " + sock.getTypeOfRequest() + "<<<<<<<<");
		if (sock.getTypeOfRequest().equals(RequestType.LOGIN)) {
			User user = sock.getUser();
			User loggedUser = AbstractOperationService.login(user);
			SocketRequest response = new SocketRequest(loggedUser,
					RequestType.LOGIN, loggedUser, false);
			return response;
		} else if (sock.getTypeOfRequest().equals(RequestType.GET_ALL_PACIENTI)) {
			SocketRequest response = new SocketRequest(sock.getUser(),
					RequestType.GET_ALL_PACIENTI, PacientService.getInstance()
							.getAllPatitens(), false);
			return response;
		} else if (sock.getTypeOfRequest().equals(RequestType.GET_ALL_USER)) {
			SocketRequest response = new SocketRequest(sock.getUser(),
					RequestType.GET_ALL_USER, UserService.getInstance()
							.getAllUsers(), false);
			return response;
		} else if (sock.getTypeOfRequest().equals(RequestType.ADD_USER)) {
			UserService.getInstance().addUser((User) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(RequestType.UPDATE_USER)) {
			UserService.getInstance().updateUser((User) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(RequestType.DELETE_USER)) {
			UserService.getInstance().deleteUser((User) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(
				RequestType.ADD_CONSULTATION_DETAILS)) {
			
			ConsultationService.getInstance().updateConsultation(
					(Consultation) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(
				RequestType.GET_ALL_PACIENTI_FOR_DOCTOR)) {
			
			SocketRequest response = new SocketRequest(sock.getUser(),
					RequestType.GET_ALL_PACIENTI_FOR_DOCTOR,
					PacientService.getInstance().getPatientsForDoctor(
							sock.getUser()), false);
			return response;
		} else if (sock.getTypeOfRequest().equals(
				RequestType.GET_ALL_CONSULTATIONS_PACIENT)) {
			
			SocketRequest response = new SocketRequest(sock.getUser(),
					RequestType.GET_ALL_CONSULTATIONS_PACIENT,
					ConsultationService.getInstance().getAllConsultationsForPacient(
							(Pacient) sock.getParameter()), false);
			return response;
		} else if (sock.getTypeOfRequest().equals(
				RequestType.ADD_PACIENT)) {
			
			PacientService.getInstance().createPacient(
					(Pacient) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(
				RequestType.UPDATE_PACIENT)) {
			
			PacientService.getInstance().updatePacient(
					(Pacient) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(
				RequestType.CREATE_CONSULTATION)) {
			
			ConsultationService.getInstance().addConsultation(
					(Consultation) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(
				RequestType.UPDATE_CONSULTATION)) {
			
			ConsultationService.getInstance().updateConsultation(
					(Consultation) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(
				RequestType.DELETE_CONSULTATION)) {
			
			ConsultationService.getInstance().removeConsultation(
					(Consultation) sock.getParameter());
		} else if (sock.getTypeOfRequest().equals(
				RequestType.GET_ALL_CONSULTATIONS)) {
			
			SocketRequest response = new SocketRequest(sock.getUser(),
					RequestType.GET_ALL_CONSULTATIONS,
					ConsultationService.getInstance().getAllConsultations(), false);
			return response;
		}
		 else if (sock.getTypeOfRequest().equals(
				RequestType.GET_ALL_DOCTORS)) {
			
			SocketRequest response = new SocketRequest(sock.getUser(),
					RequestType.GET_ALL_DOCTORS,
					UserService.getInstance().getAllDoctors(), false);
			return response;
		} 
		
		return sock;
	}

	public AbstractOperationService getOperationService(User user) {
		// login user
		User existingUser = AbstractOperationService.login(user);
		// select associated service
		if (existingUser.isAdmin())
			return UserService.getInstance();
		if (existingUser.isDoctor())
			return ConsultationService.getInstance();
		if (existingUser.isSecretary())
			return PacientService.getInstance();
		// returns when login failed
		return null;
	}

}
