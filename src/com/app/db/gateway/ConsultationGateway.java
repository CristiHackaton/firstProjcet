package com.app.db.gateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.db.ConnectionWithDB;
import com.app.db.model.Consultation;

public class ConsultationGateway {

    /**
     * idconsultation idUser duration date_of_consultation idPatient notes
     */

    // CRUD Statements
    private static String CREATE_STATEMENT = "insert into consultation  values (?,?,?,?,?,?)";
    private static String READ_BY_ID_STATEMENT = "select * from consultation where idconsultation = ?";
    private static String READ_ALL_PATIENT_STATEMENT = "select * from consultation";
    private static String UPDATE_BY_ID_STATEMENT = "update consultation set client_name = ?, address = ? where idconsultation=? ";
    private static String REMOVE_STATEMENT = "delete from consultation where idconsultation = ?";

    public ConsultationGateway() {

    }

    public boolean addConsulation(final Consultation consultation) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement addStatement = con.prepareStatement(CREATE_STATEMENT);
            addStatement.setInt(0, consultation.getId());
            addStatement.setInt(1, consultation.getDoctor().getUserID());
            addStatement.setFloat(2, consultation.getDuration());
            addStatement.setDate(3, new Date(consultation.getConsultationDate().getTime()));
            addStatement.setInt(4, consultation.getPacient().getId());
            addStatement.setString(5, consultation.getNotes());

            addStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Consultation cannot be added!");
            return false;
        }

        return true;
    }

}
