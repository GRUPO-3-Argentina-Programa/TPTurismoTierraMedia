package app;

import java.sql.SQLException;

import model.Atraccion;
import model.Usuario;
import dao.AtraccionDao;
import dao.PromocionDao;
import dao.UsuarioDao;


public class App {
	
	public static void main(String[] args) throws SQLException {
		
//		Usuario prueba = new Usuario("prueba", 500, 3, "TERROR");
//		System.out.println(UsuarioDao.insert(prueba));
		
		
	//	System.out.println(UsuarioDao.findAll());
		
//		System.out.println(UsuarioDao.borrarUsuario("prueba"));
		
//		System.out.println(AtraccionDao.findAll());
		
//	    
//		
//		Atraccion Moria = AtraccionDao.findByName("Moria");
//			
//		Moria.restarCupo();
//		
//        AtraccionDao.updateCupo(Moria);
//     	 System.out.println(Moria.getCupo());
//		 
	//	 System.out.println(UsuarioDao.insert(prueba));
     	 
     	 
     System.out.println(PromocionDao.creadorLista(1));
	}
	
	

}
