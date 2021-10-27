package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.sun.tools.javac.util.List;

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
	
	public static List<Sugerible> getItinerario(String nombre) {
		String query = "SELECT * FROM ITINERARIO WHERE NOMBRE LIKE ?";
		Connection conn= ConnectionProvider.getConnection();		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, nombre);
		
		ResultSet result= statement.executeQuery();
		
		List<Sugerible> sugerencias = new LinkedList<Sugerible>(); 
		while(result.next()) {
			if(result.getRowId(2).equals(null)) {
				sugerencias.add(PromocionDao.findByIdPromo((result.getRowId(3))));
			} else {
				sugerencias.add(AtraccionDao.findById(result));
			}
			
		//	System.out.println(result.getString(1) +" "+ result.getInt(2));
		}
				
		return sugerencias;
	}

}
