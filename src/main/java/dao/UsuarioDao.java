package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import jdbc.ConnectionProvider;
import model.Usuario;

public class UsuarioDao {
	
	private static Usuario toUsuario(ResultSet result) throws SQLException {
		return new Usuario(result.getString(1), result.getInt(2),
				result.getDouble(3), result.getString(4));
	}
	
	public static int insert(Usuario usuario) throws SQLException {
		String query = "INSERT INTO USUARIOS (nombre, presupuesto, tiempoDisponible, preferencia) VALUES (?, ?, ?, ?)";
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
	// to do
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
	
	//no chequeado en app
	public static int updateUsuario(Usuario usuario) throws SQLException {
	String query = "UPDATE usuarios SET tiempoDisponible = ?, presupuesto = ? ";
	Connection conn= ConnectionProvider.getConnection();
	
	PreparedStatement statement = conn.prepareStatement(query);
	
	statement.setDouble(1, usuario.getTiempoDisponible());
	statement.setDouble(2, usuario.getPresupuesto());
	
	return statement.executeUpdate();

	}
	
}

