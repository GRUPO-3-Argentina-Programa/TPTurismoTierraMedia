package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;


public class PromocionDao {
	
	List<Atraccion> atraccionesDePromo = null;




	private Promocion toPromocion(ResultSet result) throws SQLException {
		return new Promocion(result.getString(2), atraccionesDePromo);
	}
	
	public static List<Atraccion>CreadorLista() throws SQLException {
		String query =  "   SELECT *\r\n"
				+ "FROM Promociones_con_atracciones pca2  \r\n"
				+ "JOIN  Atracciones a  ON  pca2.Id_atraccion  = a.atraccion_id \r\n";
		Connection conn= ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		
		ResultSet result= statement.executeQuery();
		
		List<Atraccion> atraccionesDePromo = new LinkedList<Atraccion>(); 
		while (result.next()) {
			
			atraccionesDePromo.add(AtraccionDao.toAtraccion(result));
		
		}
				
		return atraccionesDePromo;
	}

	//todo
	public static int updateCupo(LinkedList<Atraccion> atraccionesDePromo) throws SQLException {
	String query = "UPDATE atracciones SET Cupo = ? ";
	Connection conn= ConnectionProvider.getConnection();
	
	PreparedStatement statement = conn.prepareStatement(query);
	
	Iterator<Atraccion> itr = atraccionesDePromo.iterator();
	Atraccion atraccion;
	while (itr.hasNext())
		itr.next().statement.setInt(1, atraccion.getCupo());
	    return statement.executeUpdate();

	}	
	@Override
	public String toString() {
		return "PromocionDao [atraccionesDePromo=" + atraccionesDePromo + "]";
	}


}
	
	
	
	
	
	
	

