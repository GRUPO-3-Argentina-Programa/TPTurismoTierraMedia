package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbs;
import model.PromocionAxB;
import model.PromocionPorcentual;

public class PromoDao2 {
	private List<Atraccion> atraccionesDePromo = new ArrayList<Atraccion>();
	
	public Promocion toPromo(ResultSet result) throws SQLException {
		if (result.getString(3).equals("porcentual")) {
			return new PromocionPorcentual(result.getString(2), atraccionesDePromo, result.getDouble(4));
		}
		if (result.getString(3).equals("AxB")) {
			return new PromocionAxB(result.getString(2), atraccionesDePromo,
					AtraccionDao.findById(result.getInt(6)));
		} else {
			return new PromocionAbs(result.getString(2), atraccionesDePromo, result.getInt(5));
		}
	}
	
	public static List<Atraccion>findAll() throws SQLException {
		AtraccionDao atr = new AtraccionDao();
		
		String query = "SELECT * FROM promociones";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		ResultSet result= statement.executeQuery();
		
		
		
		while(result.next()) {
			
			atracciones.add(atr.toAtraccion(result));
		//	System.out.println(result.getString(1) +" "+ result.getInt(2));
		}
				
		return atracciones;
	}
	
	private List<Atraccion> getAtraccionesDePromo(List<Integer> idAtracciones,
			List<Atraccion> nombresAtracciones) {
		this.atraccionesDePromo = new LinkedList<Atraccion>();
		for (Integer id : idAtracciones) {
			for (Atraccion a : nombresAtracciones) {
				if (id == a.getIdAtraccion()) {
					atraccionesDePromo.add(a);
				}
			}
		}
		return atraccionesDePromo;
	}S

}
