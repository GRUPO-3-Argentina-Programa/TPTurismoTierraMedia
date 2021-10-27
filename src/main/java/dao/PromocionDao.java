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
import model.PromocionPorcentual;
import model.PromocionAxB;
import model.PromocionAbs;

public class PromocionDao {

	static List<Atraccion> atraccionesDePromo = null;

	public static List<Promocion> findAllPromo() throws SQLException {
		String query = "SELECT * FROM promociones";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(query);

		ResultSet result = statement.executeQuery();

		List<Promocion> promocion = new LinkedList<Promocion>();
		while (result.next()) {

			promocion.add(toTipoPromocion(result));

		}

		return promocion;
	}

	private static Promocion toTipoPromocion(ResultSet result) throws SQLException {
		atraccionesDePromo = new LinkedList<Atraccion>();
		atraccionesDePromo.addAll(getAtraccionesDePromo(getIdDeAtracciones(result.getInt(1))));

		if (result.getString(3).equals("porcentual")) {
			return new PromocionPorcentual(result.getString(2), atraccionesDePromo, result.getDouble(4));
		}

		if (result.getString(3).equals("AxB")) {
			return new PromocionAxB(result.getString(2), atraccionesDePromo, AtraccionDao.findById(result.getInt(6)));
		} 
		else {
			return new PromocionAbs(result.getString(2), atraccionesDePromo, result.getInt(5));
		}
	}

	private static List<Atraccion> getAtraccionesDePromo(List<Integer> id_atracciones) throws SQLException {
		List<Atraccion> atracciones = new LinkedList<Atraccion>();
		
		for (Integer id : id_atracciones) {
			for (Atraccion a : AtraccionDao.findAll()) {
				if (id == a.getIdAtraccion()) {
					atracciones.add(a);
				}
			}
		}
		return atracciones;
	}

	public static List<Integer> getIdDeAtracciones(int id_promocion) throws SQLException {
		String query = "   SELECT Id_atraccion FROM Promociones_con_atracciones  \r\n" + "WHERE id_promocion = ? ";
		Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);

		statement.setInt(1, id_promocion);
		ResultSet result = statement.executeQuery();

		List<Integer> id_atracciones = new LinkedList<Integer>();
		while (result.next()) {
			id_atracciones.add(result.getInt(1));
		}

		return id_atracciones;
	}

}
