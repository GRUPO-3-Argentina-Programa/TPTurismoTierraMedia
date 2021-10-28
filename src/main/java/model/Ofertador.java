package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Ofertador {
	
	public void ofertar(Usuario us, List<Sugerible> sugerencias) throws IOException, SQLException {
	sugerencias.sort(new ComparadorDeSugerencias(us.getTipo()));


	Iterator<Sugerible> sg = sugerencias.iterator();
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	while (sg.hasNext()) {
		Sugerible sug = sg.next();
		
		if (us.puedeComprar(sug) && sug.hayCupo()) {
			System.out.println("\n Usuario: " + us.getNombre() + ", tiempo disponible: "+ us.getTiempoDisponible() +
					", presupuesto disponible: "+ String.format("%.2f", us.getPresupuesto())+
					"\nSe sugiere: "+sug.toString() +
					"\n-Presione 1 si acepta, sino presione cualquier otro numero.");

			if (sc.next().equals("1")) {
				sug.restarCupo();
				us.aceptarSugerencia(sug);
			}
		}
	}
	System.out.println("--------------Fin de sugerencias--------------\n");
	System.out.println(us.getNombre()+", su itinerario incluye: " + us.itinerario+ 
			"\n\nEl costo total es: " + us.getTotalPagar(us.itinerario) + 
			"\nEl tiempo total necesario es: " + us.getTotalTiempo(us.itinerario));
	System.out.println("\n----------------------------------------------");
	System.out.println("\nPresione Enter para continuar");
	
	System.in.read();
	}

}
