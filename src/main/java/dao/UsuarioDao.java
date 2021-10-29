package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.Sugerible;
import model.Usuario;

public class UsuarioDao {
	
	public static void guardar(Usuario usuario, Sugerible sugerencia) throws SQLException {
		Connection conn= ConnectionProvider.getConnection();
		
		try {
			conn.setAutoCommit(false);
			ItinerarioDao.insert(usuario, sugerencia, conn);
			updateUsuario(usuario, conn);
			if(!sugerencia.esPromo()) {
				AtraccionDao.updateCupo((Atraccion) sugerencia, conn);
			} else {
				PromocionDao.updateCupo((Promocion) sugerencia, conn);
			}
			
		} catch (SQLException e) {
			System.out.println("No se pudo realizar la transaccion");
			conn.rollback();
		} finally {
			conn.commit();
		}
		
		
	}
	
	private static Usuario toUsuario(ResultSet result) throws SQLException {
		return new Usuario(result.getString(1), result.getInt(2),
				result.getDouble(3), result.getString(4));
	}
	
	public static int insert(Usuario usuario) throws SQLException {
		String query = "INSERT INTO USUARIOS (nombre, presupuesto_disponible, tiempo_disponible, preferencia) VALUES (?, ?, ?, ?)";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, usuario.getNombre());
		statement.setDouble(2, usuario.getPresupuesto());
		statement.setDouble(3, usuario.getTiempoDisponible());
		statement.setString(4, usuario.getTipo());
		
	// insertar si no existe
		// if(findByNombreAndBanda(cancion.getCancion(), cancion.getBanda() == null))
		return statement.executeUpdate();
		// else return 0;
		}
	
	public static List<Usuario>findAll() throws SQLException {
		String query = "SELECT * FROM usuarios";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		ResultSet result= statement.executeQuery();
		
		List<Usuario> usuario = new LinkedList<Usuario>(); 
		while(result.next()) {
			
			usuario.add(toUsuario(result));
		//	System.out.println(result.getString(1) +" "+ result.getInt(2));
		}
				
		return usuario;
	}
	
	public static int borrarUsuario(String nombre) throws SQLException {
		String query = "DELETE FROM usuarios WHERE NOMBRE LIKE ?";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, nombre);
			
		return statement.executeUpdate();

		}
	
	public static int updateUsuario(Usuario usuario, Connection conn) throws SQLException {
		String query = "UPDATE usuarios SET tiempo_disponible = ?, presupuesto_disponible = ? WHERE nombre LIKE ?";
		
		PreparedStatement statement = conn.prepareStatement(query);

		statement.setDouble(1, usuario.getTiempoDisponible());
		statement.setDouble(2, usuario.getPresupuesto());
		statement.setString(3, usuario.getNombre());
		
		return statement.executeUpdate();

	}
	
	public static Usuario findByNombre(String nombre) throws SQLException {
		String query = "SELECT * FROM usuarios WHERE nombre LIKE ?";
		Connection conn= ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, nombre);
		
		ResultSet result= statement.executeQuery();
		
		Usuario nombreU = null;
		if(result.next()) {
			nombreU = toUsuario(result);
		}
		
		return nombreU;
	}

}

