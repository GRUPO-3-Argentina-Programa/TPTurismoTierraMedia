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
	private List<Atraccion> idAtracciones = null;

	public static List<Promocion> findAllPromo() throws SQLException {
		String query = "SELECT * FROM promociones";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(query);

		ResultSet result = statement.executeQuery();

		List<Promocion> promocion = new LinkedList<Promocion>();
		while (result.next()) {

			promocion.add(toPromocion(result));
			// System.out.println(result.getString(1) +" "+ result.getInt(2));
		}

		return promocion;
	}

	private static Promocion toPromocion(ResultSet result) throws SQLException {
		if (result.getString(3).equals("porcentual")) {
			return new PromocionPorcentual(result.getString(2), this.atraccionesDePromo, result.getDouble(4));
		}
		if (result.getString(3).equals("AxB")) {
			return new PromocionAxB(result.getString(2), this.atraccionesDePromo,
					AtraccionDao.findById(result.getInt(6)));
		} else {
			return new PromocionAbs(result.getString(2), this.atraccionesDePromo, result.getInt(5));
		}
	}

	private static List<Atraccion> getAtraccionesDePromo(List<Integer> idAtracciones,
			List<Atraccion> nombresAtracciones) {
		for (Integer id : idAtracciones) {
			for (Atraccion a : nombresAtracciones) {
				if (id == a.getIdAtraccion()) {
					atraccionesDePromo.add(a);
				}
			}
		}
		return atraccionesDePromo;
	}

	public static List<Integer> getIdDeAtracciones(Integer idPromo) throws SQLException {
		String query = "   SELECT Id_atraccion FROM Promociones_con_atracciones  \r\n" + "WHERE id_promocion = ? ";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);

		statement.setInt(1, idPromo);
		ResultSet result = statement.executeQuery();

		List<Integer> id_atracciones = new LinkedList<Integer>();
		while (result.next()) {

			id_atracciones.add(result.getInt(1));

		}

		return id_atracciones;
	}

	// todo
	public static int updateCupo(LinkedList<Atraccion> atraccionesDePromo) throws SQLException {
		String query = "UPDATE atracciones SET Cupo = ? ";
		Connection conn = ConnectionProvider.getConnection();

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
