package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection connection;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/ibms?characterEncoding=UTF-8",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
