package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AppApprovalTable extends SQLTable {

	private static AppApprovalTable instance = null;
	
	private AppApprovalTable() {
		this.table = "AppApproval";
	}
	
	public static AppApprovalTable getInstance() {
		if(instance == null) {
			instance = new AppApprovalTable();
		}
		return instance;
	}
}
