package model;

import java.util.*;

public class Promocion implements Sugerible {
	protected List<Atraccion> atraccionesDePromo;
	protected String tipoAtraccion;
	protected int cupo;
	protected double tiempoTotal;
	protected double sumaCostos = 0;
	protected String[] nombresDeAtracciones;
	private int id;

	public Promocion(String tipo, List<Atraccion> atraccionesDePromo, int Id) {
		this.tipoAtraccion = tipo;
		this.atraccionesDePromo = new LinkedList<Atraccion>(atraccionesDePromo);
		this.id = Id;
	}

	public String getTipoAtraccion() {
		return tipoAtraccion;
	}

	public List<Atraccion> getAtracciones() {
		return atraccionesDePromo;
	}

	public double getTiempoTotal() {
		tiempoTotal = 0;
		for (Atraccion a : this.atraccionesDePromo)
			tiempoTotal += a.getTiempoTotal();

		return tiempoTotal;
	}

	public double getCosto() {
		sumaCostos = 0;
		for (Atraccion a : atraccionesDePromo) {
			sumaCostos += a.getCosto();
		}
		return sumaCostos;
	}
	
	public boolean esPromo() {
		return true;
	}

	public void restarCupo() {
		Iterator<Atraccion> itr = this.atraccionesDePromo.iterator();
		while (itr.hasNext())
			itr.next().restarCupo();
	}

	public boolean hayCupo() {
		Iterator<Atraccion> itr = this.atraccionesDePromo.iterator();
		while (itr.hasNext())
			if (!itr.next().hayCupo())
				return false;
		return true;
	}

	public String[] getNombresDeAtracciones() {
		int size = getAtracciones().size();
		nombresDeAtracciones = new String[size];
		for (int i = 0; i < size; i++) {
			nombresDeAtracciones[i] = getAtracciones().get(i).getNombre();
		}
		return nombresDeAtracciones;
	}

	public String getNombre() {
		return "Esto es una promoción que incluye: " + Arrays.toString(getNombresDeAtracciones());
	}


	public String toString() {

		return "\n Promocion: " + Arrays.toString(getNombresDeAtracciones()) + "\nTipo de Atracciones= " + tipoAtraccion
				+ "\nCosto: " + String.format("%.2f", getCosto()) + ", Tiempo: " + tiempoTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atraccionesDePromo, cupo, sumaCostos, tiempoTotal, tipoAtraccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesDePromo, other.atraccionesDePromo) && cupo == other.cupo
				&& Double.doubleToLongBits(sumaCostos) == Double.doubleToLongBits(other.sumaCostos)
				&& Double.doubleToLongBits(tiempoTotal) == Double.doubleToLongBits(other.tiempoTotal)
				&& tipoAtraccion == other.tipoAtraccion;
	}

	
	public int getId() {
		return id;
	}

}
