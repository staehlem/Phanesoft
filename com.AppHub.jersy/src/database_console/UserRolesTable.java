package database_console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class UserRolesTable extends SQLTable {
	
	private static UserRolesTable instance = null;
	
	private UserRolesTable() {
		this.table = "UserRoles";
	}
	
	public static UserRolesTable getInstance() {
		if(instance == null) {
			instance = new UserRolesTable();
		}
		return instance;
	}
	
	public String searchTable(String searchField, String search) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE " + searchField + "='" + search+"'";
			ResultSet rs = stmt.executeQuery( SQL );
			
			if(rs.next()) {
				return UserTypesTable.getUserTypesTable().searchTable("UserType",
						"'"+rs.getString("UserType")+"'").getUserType();
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String updateUser(User user) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE username='" + user.getUserName()+"'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.updateString("UserType", UserTypesTable.getUserTypesTable().searchTable("UserType",
						"'"+user.getUserType()+"'").getUserType());
				
				rs.updateRow();
				stmt.close();
				rs.close();
				con.close();
				return searchTable("username", user.getUserName());
			}
			else {
				System.out.println("No user found with that username!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String newUser(User user) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			
			rs.moveToInsertRow();
			
			rs.updateString("username", user.getUserName());
			rs.updateString("UserType", UserTypesTable.getUserTypesTable().searchTable("UserType",
					"'"+user.getUserType()+"'").getUserType());
			
			rs.insertRow();
			stmt.close();
			rs.close();
			con.close();
			
			return searchTable("username", user.getUserName());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean deleteUser(String userId) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE username='" + userId+"'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.deleteRow();
				stmt.close();
				rs.close();
				con.close();
				return true;
			}
			else {
				System.out.println("No user found with that username!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
