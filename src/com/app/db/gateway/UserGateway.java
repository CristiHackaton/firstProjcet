package com.app.db.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.db.ConnectionWithDB;
import com.app.db.model.Pacient;
import com.app.db.model.User;

public class UserGateway {

    // CRUD Statements
    private static String CREATE_STATEMENT = "insert into user values (?,?,?,?,?)";
    private static String READ_BY_ID_STATEMENT = "select * from user where idUser = ?";
    private static String READ_BY_ID_USER_NAME_PASSWORD = "select * from user where user_name = ? and password = ?";
    private static String UPDATE_BY_ID_STATEMENT = "update user set user_name = ?, password = ?, email = ? where idUser=? ";
    private static String REMOVE_STATEMENT = "delete from user where idUser = ?";
    private static String GET_ALL_USERS = "select * from user";
    
    public UserGateway() {
    }

    public boolean addUser(final User userToAdd) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement addStatement = con.prepareStatement(CREATE_STATEMENT);
            addStatement.setInt(1, userToAdd.getUserID());
            addStatement.setString(2, userToAdd.getUsername());
            addStatement.setString(3, userToAdd.getPassword());
            addStatement.setString(4, userToAdd.getEmail());
            addStatement.setInt(5, userToAdd.getUserType());

            addStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("User cannot be added!");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public User getUserById(final int id) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement getStatement = con.prepareStatement(READ_BY_ID_STATEMENT);
            getStatement.setInt(1, id);
            ResultSet result = getStatement.executeQuery();

            if (result.first()) {
                User user = new User();
                user.setUserID(result.getInt("idUser"));
                user.setUsername(result.getString("user_name"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setUserType(result.getInt("type"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("User cannot be found with id " + id);
            return null;
        }

    }

    public User getUserByNameAndPassword(final String username, final String password) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement getStatement = con.prepareStatement(READ_BY_ID_USER_NAME_PASSWORD);
            getStatement.setString(1, username);
            getStatement.setString(2, password);
            ResultSet result = getStatement.executeQuery();

            if (result.first()) {
                User user = new User();
                user.setUserID(result.getInt("idUser"));
                user.setUsername(result.getString("user_name"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setUserType(result.getInt("type"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("User cannot be found with username " + username);
            return null;
        }
    }

    public boolean updateUser(final User us) {
        Connection con = ConnectionWithDB.getInstance();

        try {

            PreparedStatement updateStatement = con.prepareStatement(UPDATE_BY_ID_STATEMENT);
            updateStatement.setString(1, us.getUsername());
            updateStatement.setString(2, us.getPassword());
            updateStatement.setString(3, us.getEmail());
            updateStatement.setInt(4, us.getUserID());
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("User cannot be updated with username " + us.getUsername());
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean removeUser(final int id) {
        Connection con = ConnectionWithDB.getInstance();
        try {

            PreparedStatement deleteStatement = con.prepareStatement(REMOVE_STATEMENT);
            deleteStatement.setInt(1, id);

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User cannot be removed with id " + id);
            return false;
        }

        return true;
    }

	public ArrayList<User> getAllUsers() {
		ArrayList<User> usersList = new ArrayList<User>();
        Connection con = ConnectionWithDB.getInstance();

        try {

            PreparedStatement getStatement = con.prepareStatement(GET_ALL_USERS);
            ResultSet result = getStatement.executeQuery();
            while (result.next()) {
               User user = new User();
               user.setUserID(result.getInt("idUser"));
               user.setUsername(result.getString("user_name"));
               user.setPassword(result.getString("password"));
               user.setEmail(result.getString("email"));
               user.setUserType(result.getInt("type"));
               usersList.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Users cannot be retrived from DB!!");
            return null;
        }

        return usersList.size() != 0 ? usersList : null;
		
	}

}
