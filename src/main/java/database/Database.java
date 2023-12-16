package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	public Connection getConnection() throws SQLException {
		
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_deal","springstudent","springstudent");
		return connection;
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(CallableStatement cs) {
		try {
			cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
