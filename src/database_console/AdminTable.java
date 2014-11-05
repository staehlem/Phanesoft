package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AdminTable extends SQLTable {
	
	private static AdminTable instance = null;
	
	private AdminTable() {
		this.table = "Admin";
	}
	
	public static AdminTable getInstance() {
		if(instance == null) {
			instance = new AdminTable();
		}
		return instance;
	}
	
}
