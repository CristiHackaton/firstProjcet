package com.app.db.gateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.db.ConnectionWithDB;
import com.app.db.model.Pacient;
import com.app.gui.PatientListModel;

public class PacientGateway {

	/*
	 * idpatient client_name cnp identity_card birth address
	 */

	// CRUD Statements
	private static String CREATE_STATEMENT = "insert into patient  values (?,?,?,?,?,?)";
	private static String READ_BY_ID_STATEMENT = "select * from patient where idpatient = ?";
	private static String READ_ALL_PATIENT_STATEMENT = "select * from patient";
	private static String UPDATE_BY_ID_STATEMENT = "update patient set client_name = ?, address = ? where idpatient=? ";
	private static String REMOVE_STATEMENT = "delete from patient where idpatient = ?";
	private static String GET_PATIENTS_FOR_DOCTOR = "select distinct idpatient from consultation where idUser = ?";
	
	public PacientGateway() {

	}

	public boolean addPacient(final Pacient pacientToAdd) {
		Connection con = ConnectionWithDB.getInstance();

		try {
			PreparedStatement addStatement = con
					.prepareStatement(CREATE_STATEMENT);
			addStatement.setInt(1, pacientToAdd.getId());
			addStatement.setString(2, pacientToAdd.getName());
			addStatement.setString(3, pacientToAdd.getCnp());
			addStatement.setString(4, pacientToAdd.getIdentitiCard());
			addStatement
					.setDate(5, new Date(pacientToAdd.getBirth().getTime()));
			addStatement.setString(6, pacientToAdd.getAddress());

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
			PreparedStatement getStatement = con
					.prepareStatement(READ_BY_ID_STATEMENT);
			getStatement.setInt(1, id);
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
			e.printStackTrace();
			return null;
		}
	}

	public boolean updatePacient(final Pacient pacient) {
		Connection con = ConnectionWithDB.getInstance();

		try {

			PreparedStatement updateStatement = con
					.prepareStatement(UPDATE_BY_ID_STATEMENT);
			updateStatement.setString(1, pacient.getName());
			updateStatement.setString(2, pacient.getAddress());
			updateStatement.setInt(3, pacient.getId());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Pacient cannot be updated with username "
					+ pacient.getName());
			return false;
		}
		return true;

	}

	public ArrayList<Pacient> getAllPacients() {
		ArrayList<Pacient> pacientList = new ArrayList<Pacient>();
		Connection con = ConnectionWithDB.getInstance();

		try {

			PreparedStatement getStatement = con
					.prepareStatement(READ_ALL_PATIENT_STATEMENT);
			ResultSet result = getStatement.executeQuery();
			while (result.next()) {
				Pacient pacient = new Pacient();
				pacient.setId(result.getInt("idpatient"));
				pacient.setName(result.getString("client_name"));
				pacient.setCnp(result.getString("cnp"));
				pacient.setIdentitiCard(result.getString("identity_card"));
				pacient.setBirth(result.getDate("birth"));
				pacient.setAddress(result.getString("address"));

				pacientList.add(pacient);
			}

		} catch (SQLException e) {
			System.out.println("Pacients cannot be retrived from DB!!");
			return null;
		}

		return pacientList.size() != 0 ? pacientList : null;
	}

	public boolean removePacient(final int id) {
		Connection con = ConnectionWithDB.getInstance();
		try {

			PreparedStatement deleteStatement = con
					.prepareStatement(REMOVE_STATEMENT);
			deleteStatement.setInt(1, id);

			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Pacient cannot be removed with id " + id);
			return false;
		}

		return true;
	}

	public ArrayList<Pacient> getPatientsForDoctor(int userID) {
		ArrayList<Pacient> pacientList = new ArrayList<Pacient>();
		Connection con = ConnectionWithDB.getInstance();
		try {

			PreparedStatement stmt = con
					.prepareStatement(GET_PATIENTS_FOR_DOCTOR);
			stmt.setInt(1, userID);

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				Pacient pacient = getPacientById(result.getInt("idpatient"));
				pacientList.add(pacient);
			}
		} catch (SQLException e) {
			System.out.println("Pacient cannot be removed with id " + userID);
		}
		return pacientList;
	}
}
