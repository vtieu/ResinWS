package rlpResinWS.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBLogin
{
	static public boolean login(String name, String password)
	{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Connection connection = DBConnection.getConnection();

		try {
			pstmt = connection.prepareStatement("select password from resindb.users where userName = ?");
			pstmt.setNString(1, name);
			
			if (!pstmt.execute())
				return false;

			resultSet = pstmt.getResultSet();
			
			if (resultSet.next()) {
				if (resultSet.getString("password").equals(password)) {
					return true;
				}
				else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt);

			DBConnection.close(resultSet);

			DBConnection.release(connection);
		}

		return false;
	}
}
