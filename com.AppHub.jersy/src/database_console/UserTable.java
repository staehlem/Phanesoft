package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.*;

public class UserTable extends SQLTable {

	private static UserTable instance = null;
	
	private UserTable() {
		this.table = "User";
	}
	
	public static UserTable getInstance() {
		if(instance == null) {
			instance = new UserTable();
		}
		return instance;
	}
	
	public User searchTable(String searchField, String search) {
		User user = null;
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE " + searchField + "=" + search;
			ResultSet rs = stmt.executeQuery( SQL );
			
			if(rs.next()) {
				user = new User(rs.getString("username"), rs.getString("firstName"), 
						rs.getString("lastName"), rs.getString("email"),
						rs.getString("password"), 
						UserRolesTable.getInstance().searchTable("username",
								rs.getString("username")));
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public User updateUser(User user) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE username='" + user.getUserName()+"'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.updateString("password", user.getPassword());
				rs.updateString("email", user.getEmail());
				rs.updateString("firstName", user.getFirst());
				rs.updateString("lastName", user.getLast());
				
				rs.updateRow();
				stmt.close();
				rs.close();
				con.close();
				
				UserRolesTable.getInstance().updateUser(user);
				
				return searchTable("username", "'"+user.getUserName()+"'");
			}
			else {
				System.out.println("No user found with that username!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User newUser(User user) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			
			rs.moveToInsertRow();
			
			rs.updateString("password", user.getPassword());
			rs.updateString("email", user.getEmail());
			rs.updateString("firstName", user.getFirst());
			rs.updateString("lastName", user.getLast());
			rs.updateString("username", user.getUserName());
			
			rs.insertRow();
			stmt.close();
			rs.close();
			con.close();
			
			UserRolesTable.getInstance().newUser(user);
			
			return searchTable("username", "'"+user.getUserName()+"'");
			
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
				UserRolesTable.getInstance().deleteUser(userId);
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
	
	public ArrayList<User> getAllUsers() {
		try {
			ArrayList<User> users = new ArrayList<User>();
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			while(rs.next()) {
				String userType = UserRolesTable.getInstance().searchTable("username",
						rs.getString("username"));
				User user = new User(rs.getString("username"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("email"),
						rs.getString("password"), userType);
				users.add(user);
			}
			stmt.close();
			rs.close();
			con.close();
			
			return users;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
