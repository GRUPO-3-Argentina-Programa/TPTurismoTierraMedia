package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import model. *;

public class ItinerarioDao {
	
	
	public static int insert(Usuario usuario, Sugerible sugerencia, Connection conn1) throws SQLException {
		String query = "INSERT INTO ITINERARIO (usuario, Atraccion, Promocion) VALUES (?, ?, ?)";
		Connection conn= ConnectionProvider.getConnection();		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, usuario.getNombre());
		if (!sugerencia.esPromo()) {
			statement.setInt(2, sugerencia.getId());
		} else {
			statement.setInt(3, sugerencia.getId());
		}
		
		return statement.executeUpdate();

	}

}
