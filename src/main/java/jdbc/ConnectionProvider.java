package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	private static String url = "jdbc:sqlite:C:\\joaco\\ARGENTINA PROGRAMA COSITAS\\TIERRA MEDIA TP GRUPAL\\DB\\turismoTierraMedia.db"; 

	private static Connection connection;
	
	//jdbc:sqlite:C:\Users\JUAN PABLO\Desktop\ARGENTINA PROGRAMA\BD\turismoTierraMedia.db
	//jdbc:sqlite:C:\Users\Diego\Argentina_Programa\SQL\turismoTierraMedia.db
	//jdbc:sqlite:C:\joaco\ARGENTINA PROGRAMA COSITAS\TIERRA MEDIA TP GRUPAL\DB\turismoTierraMedia.db

	public static Connection getConnection() throws SQLException {
		 		if (connection == null) {
				 connection = DriverManager.getConnection(url);
			}
			return connection;
		}
}


