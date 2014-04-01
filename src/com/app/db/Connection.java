package com.app.db;

import java.sql.DriverManager;
import java.sql.SQLException;

	public class Connection {
		
			  private static Connection connect = null;
			
			  private Connection(){
				  try {
					Class.forName("com.mysql.jdbc.Driver");
					connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/Bank?"
							+ "user=root&password=root");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				  
			  }
			  public static Connection getInstance(){
				  if(connect==null){
					  new Connection();
				  }
				  return connect;  
			  }
}
