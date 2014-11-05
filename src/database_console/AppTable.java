package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

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
}
