package com.resinWS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLTest {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public String readDataBase() throws Exception {
		String name = "<none>";
		try {
			// Load the MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Create the DB connection
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost/world?user=root&password=password");

			statement = connect.createStatement();
			
			resultSet = statement.executeQuery("select * from world.country order by name");
			
			if (resultSet.next()) {
				name = resultSet.getString("name");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		
		return name;

	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}

