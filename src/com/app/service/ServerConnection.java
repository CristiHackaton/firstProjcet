package com.app.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.app.db.model.User;
import com.app.service.operations.AbstractOperationService;
import com.app.service.operations.ConsultationService;
import com.app.service.operations.PacientService;
import com.app.service.operations.UserService;

public class ServerConnection {
	
	private static ServerSocket listener;
	private PrintWriter out;
	
	public static void startServer() throws IOException
	{
		 ServerSocket listener = new ServerSocket(6969);
	        try {
	        	
	            while (true) {
	                Socket socket = listener.accept();
	                try {
	                  //  out =         new PrintWriter(socket.getOutputStream(), true);
	                } finally {
	                    socket.close();
	                }
	            }
	        }
	        finally {
	            listener.close();
	        }
	}
	
	public AbstractOperationService getOperationService(User user) {
		//login user
		User existingUser = AbstractOperationService.Login(user);
		//select associated service
		if(existingUser.isAdmin())
			return UserService.getInstance(); 
		if(existingUser.isDoctor())
			return ConsultationService.getInstance(); 
		if(existingUser.isSecretary())
			return PacientService.getInstance(); 
		//returns when login failed
		return null;
	}

}
