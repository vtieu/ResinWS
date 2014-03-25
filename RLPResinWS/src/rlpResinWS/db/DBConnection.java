package rlpResinWS.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection
{
	static Connection getConnection()
	{
		Connection connection = null;
		
		try {
			// Load the MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Create the DB connection
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/world?user=root&password=password");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	static void release(Connection connection)
	{
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void close(ResultSet resultSet)
	{
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void close(Statement statement)
	{
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
