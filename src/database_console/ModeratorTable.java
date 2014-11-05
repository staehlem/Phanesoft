package database_console;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ModeratorTable extends SQLTable {

	private static ModeratorTable instance = null;
	
	private ModeratorTable() {
		this.table = "Moderator";
	}
	
	public static ModeratorTable getInstance() {
		if(instance == null) {
			instance = new ModeratorTable();
		}
		return instance;
	}
}
