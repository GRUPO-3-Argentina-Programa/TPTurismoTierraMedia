package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model. *;
import dao.AtraccionDao;
import dao.ItinerarioDao;
import dao.PromocionDao;
import dao.UsuarioDao;


public class App {
	
	public static void main(String[] args) throws SQLException {
		
		List<Atraccion> atracciones;
		atracciones = AtraccionDao.findAll();

		List<Usuario> usuarios;
		usuarios = UsuarioDao.findAll();

		List<Promocion> promociones;
		promociones = PromocionDao.findAllPromo();

		List<Sugerible> sugerencias = new LinkedList<Sugerible>(atracciones);
		sugerencias.addAll(promociones);
		
		Iterator<Usuario> u = usuarios.iterator();
		
		while (u.hasNext()) {

			Usuario us = u.next();
			us.itinerario = new LinkedList<Sugerible>();

			Ofertador ofertador = new Ofertador();
			
			try {
				ofertador.ofertar(us, sugerencias);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		
	}
}
