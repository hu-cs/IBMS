package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class DBConnection {

     		JTable table;

		public DBConnection(JTable table) {
			this.table = table;
		}

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


