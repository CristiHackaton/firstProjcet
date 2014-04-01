package com.app.db.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.db.model.User;
import com.app.exception.DatabaseException;

public class UserGateway {

    // CRUD Statements
    private static String CREATE_STATEMENT = "insert into user values (?,?,?,?,?)";
    private static String READ_BY_ID_STATEMENT = "select * from user where id = ?";
    private static String READ_BY_ID_USER = "select * from user where user_name = ?";
    private static String UPDATE_BY_COLUMN_STATEMENT = "update user set user_name = ?, password = ?, email = ? where ?=? ";
    private static String REMOVE_STATEMENT = "delete from user where id = ?";

    public boolean addUser(final User userToAdd) throws DatabaseException {
        Connection con = ConnectionToDb.getConnection();

        try {
            PreparedStatement addStatement = con.prepareStatement(CREATE_STATEMENT);
            addStatement.setInt(0, userToAdd.getID());
            addStatement.setString(1, userToAdd.getUserName());
            addStatement.setString(2, userToAdd.getPassword());
            addStatement.setString(3, userToAdd.getEmail());
            addStatement.setInt(4, userToAdd.getType());

            addStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("User cannot be added!");
            return false;
        }

        return true;
    }

    public User getUserById(final int id) {
        Connection con = ConnectionToDb.getConnection();

        try {
            PreparedStatement getStatement = con.prepareStatement(READ_BY_ID_STATEMENT);
            getStatement.setInt(0, id);
            ResultSet result = getStatement.executeQuery();

            if (result.first()) {
                User user = new User();
                user.setId(result.getInt("idUser"));
                user.setUserName(result.getString("user_name"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setType(result.getInt("type"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("User cannot be added!");
            return null;
        }

    }

}
