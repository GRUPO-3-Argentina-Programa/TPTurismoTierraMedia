package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import jdbc.ConnectionProvider;

import model.Usuario;

public class UsuarioDao {
	
	private Usuario toUsuario(ResultSet result) throws SQLException {
		return new Usuario(result.getString(1), result.getInt(2),
				result.getDouble(3), result.getString(4));
	}
	
	public int insert(Usuario usuario) throws SQLException {
		String query = "INSERT INTO USUARIOS (nombre, presupuesto, tiempoDisponible, tipoDeAtraccionPreferida) VALUES (?, ?, ?, ?)";
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
	
}

