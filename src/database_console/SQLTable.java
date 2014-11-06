package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SQLTable {
	
	static protected String host = "jdbc:mysql://localhost:3306/mydb";
	static protected String username = "root";
	static protected String password = "Skylinemaster123";
	protected String table = null;
	
	public ResultSet searchTable(String searchField, String search) {
		ResultSet rs = null;
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String SQL = "SELECT * FROM " + table + " WHERE " + searchField + " LIKE %" + search + "%";
			rs = stmt.executeQuery( SQL );
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
	
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(host, username, password);
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String SQL = "SELECT * FROM App WHERE appId LIKE '%1%'";
			ResultSet rs = stmt.executeQuery( SQL );
			
			while(rs.next()) {
				int id = rs.getInt("appId");
				String appName = rs.getString("appName");
				String appDeveloper = rs.getString("appDeveloper");
				System.out.println(id+" "+appName+" "+appDeveloper);			
			}
//			
//			rs.updateInt("appId", 1);
//			rs.updateString("appName", "a");
//			rs.updateString("appDeveloper", "b");
//			rs.updateString("appDescription", "c");
//			rs.updateString("appLocation", "d");
			
//			rs.updateRow();
			
//			rs.moveToInsertRow();
//			rs.updateInt("appId", 431213);
//			rs.updateString("appName", "Cool App");
//			rs.updateString("appDeveloper", "Nathan");
//			rs.updateString("appDescription", "It is an alright app");
//			rs.updateString("appLocation", "Local");
//			
//			rs.insertRow();
//			stmt.close();
//			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
