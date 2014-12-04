package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.App;
import model.AppComments;

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
	
	private ArrayList<AppComments> getAppComments(String appId) {
		return AppCommentsTable.getInstance().getAppCommentsForApp(appId);
	}
	
	public App searchTable(String searchField, String search) {
		App app = null;
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE " + searchField + "='" + search+"'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				app = new App(rs.getString("appId"), rs.getString("appName"), rs.getString("appUrl"), 
						rs.getString("appDeveloper"), rs.getString("appDescription"), 
						PlatformTable.getInstance().searchTable("idPlatforms", rs.getInt("appPlatform")+"").getPlatformName(), rs.getBoolean("appAvailable"), rs.getDouble("appRating"), 
						rs.getDouble("appCost"), rs.getDouble("appVersion"), getAppComments(rs.getString("appId")), rs.getString("appLocalImage"));
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return app;
	}
	
	public App updateApp(App app) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE appId='" + app.getAppID()+"'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.updateString("appName", app.getAppName());
				rs.updateString("appDeveloper", app.getAppDeveloper());
				rs.updateString("appDescription", app.getDescription());
				rs.updateString("appUrl", app.getAppUrl());
				rs.updateDouble("appRating", app.getAppRating());
				rs.updateDouble("appCost", app.getAppCost());
				rs.updateString("appPlatform", PlatformTable.getInstance().searchTable("platformName", app.getAppPlatform()).getIdPlatform()+"");
				rs.updateDouble("appVersion", app.getAppVersion());
				rs.updateBoolean("appAvailable", app.isAppAvailable());
				rs.updateString("appLocalImage", app.getAppLocalImage());
				
				rs.updateRow();
				stmt.close();
				rs.close();
				con.close();
				return searchTable("appId",app.getAppID());
			}
			else {
				System.out.println("No app found with that ID!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public App newApp(App app) {
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
			rs.updateString("appPlatform", PlatformTable.getInstance().searchTable("platformName", app.getAppPlatform()).getIdPlatform()+"");
			rs.updateDouble("appVersion", app.getAppVersion());
			rs.updateBoolean("appAvailable", app.isAppAvailable());
			rs.updateString("appId", app.getAppID());
			rs.updateString("appLocalImage", app.getAppLocalImage());
			
			rs.insertRow();
			stmt.close();
			rs.close();
			con.close();
			
			return searchTable("appId",app.getAppID());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteApp(String appId) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE appId='" + appId+"'";
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
						PlatformTable.getInstance().searchTable("idPlatforms", rs.getInt("appPlatform")+"").getPlatformName(), rs.getBoolean("appAvailable"), 
						rs.getDouble("appRating"), rs.getDouble("appCost"), rs.getDouble("appVersion"), null, rs.getString("appLocalImage"));
				apps.add(app);
				//TODO: We need to add functionallity here to also call the app comments table to get
				//all of the required comments, although we may not at the same time, depends on UI choices
			}
			rs.close();
			stmt.close();
			con.close();
			return apps;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
