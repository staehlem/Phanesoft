package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.App;

public class AppTable extends SQLTable {

	private static AppTable instance = null;
	
	private AppTable() {
		this.table = "App";
	}
	
	public static AppTable getInstance() {
		if(instance == null) {
			instance = new AppTable();
		}
		return instance;
	}
	
	public Boolean updateTable(App app) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE appId=" + app.getAppID();
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.updateString("appName", app.getAppName());
				rs.updateString("appDeveloper", app.getAppDeveloper());
				rs.updateString("appDescription", app.getDescription());
				rs.updateString("appUrl", app.getAppUrl());
				rs.updateDouble("appRating", app.getAppRating());
				rs.updateDouble("appCost", app.getAppCost());
				rs.updateString("appPlatform", app.getAppPlatform());
				rs.updateDouble("appVersion", app.getAppVersion());
				rs.updateBoolean("appAvailable", app.isAppAvailable());
				
				rs.updateRow();
				stmt.close();
				rs.close();
				return true;
			}
			else {
				System.out.println("No app found with that ID!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean newColumn(App app) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			
			rs.moveToInsertRow();
			
			rs.updateString("appName", app.getAppName());
			rs.updateString("appDeveloper", app.getAppDeveloper());
			rs.updateString("appDescription", app.getDescription());
			rs.updateString("appUrl", app.getAppUrl());
			rs.updateDouble("appRating", app.getAppRating());
			rs.updateDouble("appCost", app.getAppCost());
			rs.updateString("appPlatform", app.getAppPlatform());
			rs.updateDouble("appVersion", app.getAppVersion());
			rs.updateBoolean("appAvailable", app.isAppAvailable());
			
			rs.insertRow();
			stmt.close();
			rs.close();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean deleteColumn(App app) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE appId=" + app.getAppID();
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.deleteRow();
				stmt.close();
				rs.close();
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
	
	public ArrayList<App> getAppList() {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			ArrayList<App> apps = new ArrayList<App>();
			while(rs.next()) {
				App app = new App(rs.getString("appId"), rs.getString("appName"), rs.getString("appUrl"), 
						rs.getString("appDeveloper"), rs.getString("appDescription"), 
						rs.getString("appPlatform"), rs.getBoolean("appAvailable"), rs.getDouble("appRating"), 
						rs.getDouble("appCost"), rs.getDouble("appVersion"));
				apps.add(app);
				//TODO: We need to add functionallity here to also call the app comments table to get
				//all of the required comments, although we may not at the same time, depends on UI choices
			}
			return apps;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
