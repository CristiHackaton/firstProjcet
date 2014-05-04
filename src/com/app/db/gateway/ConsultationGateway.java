package com.app.db.gateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.db.ConnectionWithDB;
import com.app.db.model.Consultation;
import com.app.db.model.Doctor;

public class ConsultationGateway {

    // CRUD Statements
    private static String CREATE_STATEMENT = "insert into consultation  values (?,?,?,?,?,?)";
    private static String READ_BY_ID_STATEMENT = "select * from consultation where idconsultation = ?";
    private static String READ_ALL_PATIENT_STATEMENT = "select * from consultation";
    private static String UPDATE_BY_ID_STATEMENT = "update consultation set duration = ?, date_of_consultation = ?, idUser = ?, notes = ? where idconsultation = ? ";
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

    public Consultation getConsultationById(final int id) {
        Connection con = ConnectionWithDB.getInstance();

        try {
            PreparedStatement getStatement = con.prepareStatement(READ_BY_ID_STATEMENT);
            getStatement.setInt(0, id);
            ResultSet result = getStatement.executeQuery();

            if (result.first()) {
                UserGateway usGateway = new UserGateway();
                PacientGateway pcGateway = new PacientGateway();

                Consultation consultation = new Consultation();
                consultation.setId(result.getInt("idconsultation"));
                consultation.setDoctor((Doctor)usGateway.getUserById(result.getInt("iduser")));
                consultation.setDuration(result.getFloat("duraction"));
                consultation.setConsultationDate(result.getDate("date_of_consultation"));
                consultation.setNotes(result.getString("notes"));
                consultation.setPacient(pcGateway.getPacientById(result.getInt("idPatient")));

                return consultation;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Consultation cannot be found with id " + id);
            return null;
        }
    }

    public List<Consultation> getAllConsultations() {
        List<Consultation> consultationList = new ArrayList<Consultation>();
        Connection con = ConnectionWithDB.getInstance();

        try {

            PreparedStatement getStatement = con.prepareStatement(READ_ALL_PATIENT_STATEMENT);
            ResultSet result = getStatement.executeQuery();
            UserGateway usGateway = new UserGateway();
            PacientGateway pcGateway = new PacientGateway();

            while (result.next()) {
                Consultation consultation = new Consultation();
                consultation.setId(result.getInt("idconsultation"));
                consultation.setDoctor((Doctor)usGateway.getUserById(result.getInt("iduser")));
                consultation.setDuration(result.getFloat("duraction"));
                consultation.setConsultationDate(result.getDate("date_of_consultation"));
                consultation.setNotes(result.getString("notes"));
                consultation.setPacient(pcGateway.getPacientById(result.getInt("idPatient")));

                consultationList.add(consultation);
            }

        } catch (SQLException e) {
            System.out.println("Consultations cannot be retrived from DB!!");
            return null;
        }

        return consultationList.size() != 0 ? consultationList : null;
    }

    public boolean updateConsultation(final Consultation consultation) {

        Connection con = ConnectionWithDB.getInstance();

        try {

            PreparedStatement updateStatement = con.prepareStatement(UPDATE_BY_ID_STATEMENT);
            updateStatement.setFloat(0, consultation.getDuration());
            updateStatement.setDate(1, new Date(consultation.getConsultationDate().getTime()));
            updateStatement.setInt(2, consultation.getDoctor().getUserID());
            updateStatement.setString(3, consultation.getNotes());
            updateStatement.setInt(4, consultation.getId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("consultation cannot be updated with id " + consultation.getId());
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
            System.out.println("Consultation cannot be removed with id " + id);
            return false;
        }

        return true;
    }

}