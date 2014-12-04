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

public class AppCommentsTable extends SQLTable {

	private static AppCommentsTable instance = null;
	
	private AppCommentsTable() {
		this.table = "AppComments";
	}
	
	public static AppCommentsTable getInstance() {
		if(instance == null) {
			instance = new AppCommentsTable();
		}
		return instance;
	}
	
	public AppComments searchTable(String searchField, String search) {
		AppComments appComment = null;
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE " + searchField + "='" + search + "'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				appComment = new AppComments(rs.getString("username"), rs.getString("appId"), 
						rs.getString("create_time"), rs.getString("appComment"),
						rs.getString("commentId"), rs.getBoolean("approvalComment"));
			}
			rs.close();
			stmt.close();
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return appComment;
	}
	
	public AppComments updateComment(AppComments appComment) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE commentId='" + appComment.getCommentId() + "'";
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.updateString("appComment", appComment.getAppComment());
				rs.updateString("appId", appComment.getAppId());
				rs.updateString("username", appComment.getUserName());
				rs.updateBoolean("approvalComment", appComment.isApprovalComment());
				
				rs.updateRow();
				stmt.close();
				rs.close();
				con.close();
				return searchTable("commentId",appComment.getCommentId());
			}
			else {
				System.out.println("No Comment found with that ID!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AppComments newComment(AppComments appComment) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table;
			ResultSet rs = stmt.executeQuery( SQL );
			
			rs.moveToInsertRow();
			
			rs.updateString("appComment", appComment.getAppComment());
			rs.updateString("appId", appComment.getAppId());
			rs.updateString("username", appComment.getUserName());
			rs.updateBoolean("approvalComment", appComment.isApprovalComment());
			rs.updateString("commentId", appComment.getCommentId());
			
			rs.insertRow();
			stmt.close();
			rs.close();
			con.close();
			
			return searchTable("commentId",appComment.getCommentId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteComment(String commentId) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM " + table + " WHERE commentId=" + commentId;
			ResultSet rs = stmt.executeQuery( SQL );
			if(rs.next()) {
				rs.deleteRow();
				stmt.close();
				rs.close();
				con.close();
				return true;
			}
			else {
				System.out.println("No comment found with that ID!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<AppComments> getAppCommentsForApp(String appId) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE appId='" + appId + "'";
			ResultSet rs = stmt.executeQuery( SQL );
			ArrayList<AppComments> appComments = new ArrayList<AppComments>();
			while(rs.next()) {
				AppComments appComment = new AppComments(rs.getString("username"), rs.getString("appId"), 
						rs.getString("create_time"), rs.getString("appComment"),
						rs.getString("commentId"), rs.getBoolean("approvalComment"));
				appComments.add(appComment);
				//TODO: We need to add functionallity here to also call the app comments table to get
				//all of the required comments, although we may not at the same time, depends on UI choices
			}
			rs.close();
			stmt.close();
			con.close();
			return appComments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
