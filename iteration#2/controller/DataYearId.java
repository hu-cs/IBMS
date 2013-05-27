package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBConnection;

public class DataYearId {

	private static int yearId;

	public static void main(String[] args) {

	}

	public static int getDataYearId() {
		try {

			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT year_id FROM `data_year` WHERE is_selected = 1");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				yearId = result.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return yearId;

	}

}
