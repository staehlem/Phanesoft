package database_console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.UserTypes;

public class UserTypesTable extends SQLTable {
	private static UserTypesTable instance = null;
	
	private UserTypesTable() {
		this.table = "UserTypes";
	}
	
	public static UserTypesTable getUserTypesTable() {
		if(instance == null) {
			instance = new UserTypesTable();
		}
		return instance;
	}
	
	public UserTypes searchTable(String searchField, String search) {
		UserTypes userType = null;
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE " + searchField + "=" + search;
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				userType = new UserTypes(rs.getString("UserType"));
			}
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userType;
	}
}
