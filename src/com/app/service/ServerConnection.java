package com.app.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
						System.out.println(sock.getUser().getUsername());
						if (sock.isNeedsResponse()) {
							System.out.println("needs server response");
							
							ObjectOutputStream out = new ObjectOutputStream(
									socket.getOutputStream());
							
							SocketRequest response = parseRequest(sock);
							out.writeObject(response);
							out.flush();
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
		//TODO needs implementation
		return sock;
	}

	public AbstractOperationService getOperationService(User user) {
		// login user
		User existingUser = AbstractOperationService.Login(user);
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
