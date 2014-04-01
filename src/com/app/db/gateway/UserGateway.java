package com.app.db.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.db.ConnectionWithDB;
import com.app.db.model.User;

public class UserGateway {

    // CRUD Statements
    private static String CREATE_STATEMENT = "insert into user values (?,?,?,?,?)";
    private static String READ_BY_ID_STATEMENT = "select * from user where id = ?";
    private static String READ_BY_ID_USER_NAME = "select * from user where user_name = ?";
    private static String UPDATE_BY_COLUMN_STATEMENT = "update user set user_name = ?, password = ?, email = ? where ?=? ";
    private static String REMOVE_STATEMENT = "delete from user where id = ?";

    public boolean addUser(final User userToAdd) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement addStatement = con.prepareStatement(CREATE_STATEMENT);
            addStatement.setInt(0, userToAdd.getUserID());
            addStatement.setString(1, userToAdd.getUsername());
            addStatement.setString(2, userToAdd.getPassword());
            addStatement.setString(3, userToAdd.getEmail());
            addStatement.setInt(4, userToAdd.getUserType());

            addStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("User cannot be added!");
            return false;
        }

        return true;
    }

    public User getUserById(final int id) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement getStatement = con.prepareStatement(READ_BY_ID_STATEMENT);
            getStatement.setInt(0, id);
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

    public User getUserByName(final String username) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement getStatement = con.prepareStatement(READ_BY_ID_USER_NAME);
            getStatement.setString(0, username);
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

}
