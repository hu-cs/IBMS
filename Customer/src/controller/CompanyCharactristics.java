package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import DataBase.DBConnection;

import com.mysql.jdbc.PreparedStatement;


public class CompanyCharactristics {

	public static String getName() {

		String name = "";

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Name FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				name = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return name;
	}

	public static String getFax() {

		String fax = "";

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT fax FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				fax = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fax;
	}

	public static String getWebSite() {

		String webSite = "";

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT website FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				webSite = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return webSite;
	}

	public static String getEmail() {

		String email = "";

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Email FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				email = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return email;
	}

	public static String getAddress() {

		String address = "";

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Address FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				address = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return address;
	}

	public static String getPhone() {

		String mobile = "";
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Mobile FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				mobile = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mobile;

	}

	public static String getTell() {

		String tell = "";

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Tell FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				tell = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tell;

	}

	public static String getLogo() {

		String logo = "";

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Logo FROM `company_setting` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				logo = result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logo;
	}

	public static void setName(String name) {

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update company_setting set name='"
							+ name + "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setFax(String fax) {

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update company_setting set fax='"
							+ fax + "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setWebSite(String webSite) {

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update company_setting set webSite='"
							+ webSite + "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setEmail(String email) {

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update company_setting set Email='"
							+ email + "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public static void setAddress(String address) {
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update company_setting set address='"
							+ address + "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setPhone(String mobile, String tell) {

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update company_setting set mobile='"
							+ mobile + "',tell='" + tell + "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setLogo(String url) {

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update company_setting set Logo='" + url
							+ "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
