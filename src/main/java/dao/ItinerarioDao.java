package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util. *;

import jdbc.ConnectionProvider;
import model. *;

public class ItinerarioDao {
	
	
	public static int insert(Usuario usuario, Sugerible sugerencia, Connection conn) throws SQLException {
		String query = "INSERT INTO ITINERARIO (usuario, Atraccion, Promocion) VALUES (?, ?, ?)";
			
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, usuario.getNombre());
		if (!sugerencia.esPromo()) {
			statement.setInt(2, sugerencia.getId());
		} else {
			statement.setInt(3, sugerencia.getId());
		}
		
		return statement.executeUpdate();

	}
	
	public static List<Sugerible> getItinerario(String nombre) throws SQLException {
		String query = "SELECT * FROM ITINERARIO WHERE usuario LIKE ?";
		Connection conn= ConnectionProvider.getConnection();		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, nombre);
		
		ResultSet result= statement.executeQuery();
		
		List<Sugerible> itinerario = new LinkedList<Sugerible>();
		
		while(result.next()) {
			if(!result.getBoolean(2)) {
				itinerario.add(PromocionDao.findByIdPromo(Integer.parseInt(result.getString(3))));
			} else {
				itinerario.add(AtraccionDao.findById(Integer.parseInt(result.getString(2))));
			}
		}			
		return itinerario;
	}

}
