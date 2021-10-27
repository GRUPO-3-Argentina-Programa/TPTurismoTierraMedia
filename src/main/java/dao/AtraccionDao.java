package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;


public class AtraccionDao {

	protected static Atraccion toAtraccion(ResultSet result) throws SQLException {
		return new Atraccion(result.getString(1), result.getInt(2), result.getString(3),
				result.getDouble(4), result.getInt(5), result.getInt(6));
	}

	
	public static List<Atraccion>findAll() throws SQLException {
		String query = "SELECT * FROM atracciones";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		ResultSet result= statement.executeQuery();
		
		List<Atraccion> atraccion = new LinkedList<Atraccion>(); 
		while(result.next()) {
			
			atraccion.add(toAtraccion(result));
		//	System.out.println(result.getString(1) +" "+ result.getInt(2));
		}
				
		return atraccion;
	}
	
	
	public Atraccion findByName(String nombre) throws SQLException {
		String query = "SELECT * FROM atracciones WHERE NOMBRE LIKE ?";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, nombre);
		
		ResultSet result= statement.executeQuery();
		
		Atraccion atraccionR = null;
		if(result.next()) {
			atraccionR = toAtraccion(result);
		}
		
		return atraccionR;
	}
	
	public static Atraccion findById(int id) throws SQLException {
		String query = "SELECT * FROM atracciones WHERE atraccion_id LIKE ?";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setInt(1, id);
		
		ResultSet result= statement.executeQuery();
		
		Atraccion atraccionId = null;
		if(result.next()) {
			atraccionId = toAtraccion(result);
		}
		
		return atraccionId;
	}
	
	public int updateCupo(Atraccion atraccion) throws SQLException {
		String query = "UPDATE atracciones SET Cupo = ? ";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(query);

		statement.setInt(1, atraccion.getCupo());

		return statement.executeUpdate();

	}
	
	
	
}
