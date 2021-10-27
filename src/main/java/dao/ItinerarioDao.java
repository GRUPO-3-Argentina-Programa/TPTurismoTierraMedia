package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import model. *;

public class ItinerarioDao {
	
	
	public static int insert(Usuario usuario, Sugerible sugerencia) throws SQLException {
		String query = "INSERT INTO ITINERARIO (usuario, sugerencia) VALUES (?, ?)";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, usuario.getNombre());
		statement.setString(2, sugerencia.getNombre());
		
		return statement.executeUpdate();

	}

}
