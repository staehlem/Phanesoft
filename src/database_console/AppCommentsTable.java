package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

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
}
