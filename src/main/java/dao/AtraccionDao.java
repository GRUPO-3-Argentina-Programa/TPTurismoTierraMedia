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
	
	public static int updateCupo(Atraccion atraccion, Connection conn1) throws SQLException {
		String query = "UPDATE atracciones SET Cupo = ? WHERE NOMBRE LIKE ?";
		Connection conn= ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);

		statement.setInt(1, atraccion.getCupo());
		statement.setString(2, atraccion.getNombre());

		return statement.executeUpdate();

	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		Atraccion atra = findById(1);
		
		atra.restarCupo();
		AtraccionDao.updateCupo(atra, conn);
	}
	
	
	
}
