package com.app.db.gateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.db.ConnectionWithDB;
import com.app.db.model.Pacient;

public class PacientGateway {

    /*
     * idpatient client_name cnp identity_card birth address
     */

    // CRUD Statements
    private static String CREATE_STATEMENT = "insert into patient  values (?,?,?,?,?,?)";
    private static String READ_BY_ID_STATEMENT = "select * from patient where idpatient = ?";
    private static String UPDATE_BY_ID_STATEMENT = "update patient set client_name = ?, address = ? where idpatient=? ";
    private static String REMOVE_STATEMENT = "delete from patient where idpatient = ?";

    public PacientGateway() {

    }

    public boolean addPacient(final Pacient pacientToAdd) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement addStatement = con.prepareStatement(CREATE_STATEMENT);
            addStatement.setInt(0, pacientToAdd.getId());
            addStatement.setString(1, pacientToAdd.getName());
            addStatement.setString(2, pacientToAdd.getCnp());
            addStatement.setString(3, pacientToAdd.getIdentitiCard());
            addStatement.setDate(4, new Date(pacientToAdd.getBirth().getTime()));
            addStatement.setString(5, pacientToAdd.getAddress());

            addStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Pacient cannot be added!");
            return false;
        }

        return true;
    }

    public Pacient getPacientById(final int id) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement getStatement = con.prepareStatement(READ_BY_ID_STATEMENT);
            getStatement.setInt(0, id);
            ResultSet result = getStatement.executeQuery();

            if (result.first()) {
                Pacient pacient = new Pacient();
                pacient.setId(result.getInt("idpatient"));
                pacient.setName(result.getString("client_name"));
                pacient.setCnp(result.getString("cnp"));
                pacient.setIdentitiCard(result.getString("identity_card"));
                pacient.setBirth(result.getDate("birth"));
                pacient.setAddress(result.getString("address"));

                return pacient;

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Pacient cannot be found with id " + id);
            return null;
        }
    }

    public boolean updatePacient(final Pacient pacient) {
        Connection con = ConnectionWithDB.getInstance();

        try {

            PreparedStatement updateStatement = con.prepareStatement(UPDATE_BY_ID_STATEMENT);
            updateStatement.setString(0, pacient.getName());
            updateStatement.setString(1, pacient.getAddress());
            updateStatement.setInt(2, pacient.getId());
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Pacient cannot be updated with username " + pacient.getName());
            return false;
        }
        return true;

    }

    public boolean removePacient(final int id) {
        Connection con = ConnectionWithDB.getInstance();
        try {

            PreparedStatement deleteStatement = con.prepareStatement(REMOVE_STATEMENT);
            deleteStatement.setInt(0, id);

            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Pacient cannot be removed with id " + id);
            return false;
        }

        return true;
    }
}
