package model;

import java.util.Comparator;

public class ComparadorDeSugerencias implements Comparator<Sugerible> {

	private String preferencia;

	public ComparadorDeSugerencias(String preferencia) {
		this.preferencia = preferencia;
	}

	//@Override
	public int compare(Sugerible s1, Sugerible s2) {
		// ordena por preferencia
		if (this.preferencia.equals(s1.getTipoAtraccion()) && !this.preferencia.equals(s2.getTipoAtraccion()))
			return -1;
		else if (!this.preferencia.equals(s1.getTipoAtraccion()) && this.preferencia.equals(s2.getTipoAtraccion()))
			return 1;
		// primero promocion
		else if (s1.esPromo() && !s2.esPromo())
			return -1;
		else if (s2.esPromo() && !s1.esPromo())
			return 1;
		// ordenar por costo
		else if (s1.getCosto() > s2.getCosto())
			return -1;
		else if (s1.getCosto() < s2.getCosto())
			return 1;
		// ordenar por tiempo
		else if (s1.getTiempoTotal() > s2.getTiempoTotal())
			return -1;
		else if (s1.getTiempoTotal() > s2.getTiempoTotal())
			return 1;
		else
			return 0;
	}
}
