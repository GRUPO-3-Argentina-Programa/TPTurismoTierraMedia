package app;

import java.sql.SQLException;

import model.Usuario;
import dao.UsuarioDao;

public class App {
	
	public static void main(String[] args) throws SQLException {
		
		Usuario prueba = new Usuario("prueba", 500, 3, "TERROR");
		System.out.println(UsuarioDao.insert(prueba));
		
	}
	
	

}
