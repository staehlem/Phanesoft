package database_console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.*;

public class PlatformTable extends SQLTable {
	
	private static PlatformTable instance = null;
	
	private PlatformTable() {
		this.table = "Platforms";
	}
	
	public static PlatformTable getInstance() {
		if(instance == null) {
			instance = new PlatformTable();
		}
		return instance;
	}
	
	public Platform searchTable(String searchField, String search) {
		Platform platform = null;
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE " + searchField + "='" + search + "'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				platform = new Platform(rs.getInt("idPlatforms"), rs.getString("platformName"));
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return platform;
	}
	
	public Platform updatePlatform(Platform platform) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE idPlatforms=" + platform.getIdPlatform();
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.updateString("platformName", platform.getPlatformName());
				
				rs.updateRow();
				stmt.close();
				rs.close();
				con.close();
				
				return searchTable("idPlatforms", platform.getIdPlatform()+"");
			}
			else {
				System.out.println("No app found with that ID!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Platform newPlatform(Platform platform) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			
			rs.moveToInsertRow();
			
			rs.updateInt("idPlatforms", platform.getIdPlatform());
			rs.updateString("platformName", platform.getPlatformName());
			
			rs.insertRow();
			stmt.close();
			rs.close();
			con.close();
			
			return searchTable("idPlatforms",platform.getIdPlatform()+"");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deletePlatform(String idPlatform) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE idPlatforms=" + idPlatform;
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.deleteRow();
				stmt.close();
				rs.close();
				con.close();
				return true;
			}
			else {
				System.out.println("No app found with that ID!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Platform> getPlatformList() {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			ArrayList<Platform> platforms = new ArrayList<Platform>();
			while(rs.next()) {
				Platform platform = new Platform(rs.getInt("idPlatforms"), rs.getString("platformName"));
				platforms.add(platform);
				//TODO: We need to add functionallity here to also call the app comments table to get
				//all of the required comments, although we may not at the same time, depends on UI choices.
			}
			
			rs.close();
			stmt.close();
			con.close();
			
			return platforms;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
