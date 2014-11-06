package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.User;
import model.GeneralUser;
import model.AdminUser;
import model.ModeratorUser;

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
	
	private String findUserType(User user) {
		if(user instanceof GeneralUser) {
			return "user";
		}
		else if(user instanceof ModeratorUser) {
			return "moderator";
		}
		else {
			return "admin";
		}
	}
	
	public Boolean updateTable(User user) {
		try {
			table = findUserType(user);
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE username=" + user.getUserName();
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.updateString("password", user.getPassword());
				rs.updateString("email", user.getEmail());
				rs.updateString("firstName", user.getFirst());
				rs.updateString("lastName", user.getLast());
				
				rs.updateRow();
				stmt.close();
				rs.close();
				return true;
			}
			else {
				System.out.println("No user found with that username!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean newColumn(User user) {
		try {
			table = findUserType(user);
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			
			rs.moveToInsertRow();
			
			rs.updateString("password", user.getPassword());
			rs.updateString("email", user.getEmail());
			rs.updateString("firstName", user.getFirst());
			rs.updateString("lastName", user.getLast());
			
			rs.insertRow();
			stmt.close();
			rs.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean deleteColumn(User user) {
		try {
			table = findUserType(user);
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE username=" + user.getUserName();
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.deleteRow();
				stmt.close();
				rs.close();
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
			String SQL = "SELECT * FROM admin";
			ResultSet rs = stmt.executeQuery( SQL );
			while(rs.next()) {
				AdminUser admin = new AdminUser(rs.getString("username"), rs.getString("firstName"), 
						rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
				users.add(admin);
			}
			stmt.close();
			rs.close();
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			SQL = "SELECT * FROM moderator";
			rs = stmt.executeQuery( SQL );
			while(rs.next()) {
				ModeratorUser moderator = new ModeratorUser(rs.getString("username"), rs.getString("firstName"), 
						rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
				users.add(moderator);
			}
			stmt.close();
			rs.close();
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			SQL = "SELECT * FROM user";
			rs = stmt.executeQuery( SQL );
			while(rs.next()) {
				GeneralUser generalUser = new GeneralUser(rs.getString("username"), rs.getString("firstName"), 
						rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
				users.add(generalUser);
			}
			stmt.close();
			rs.close();
			
			return users;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
