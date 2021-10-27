package model;

import java.sql.SQLException;
import java.util.*;

import dao.AtraccionDao;
import dao.ItinerarioDao;

public class Usuario {

	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoDeAtraccionPreferida;
	public List<Sugerible> itinerario;
	private final double TIEMPO;
	private final double PRESUPUESTO;
	protected double totalPagar;
	protected double totalTiempo;

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, String tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccionPreferida = tipoDeAtraccionPreferida;
		this.itinerario = new LinkedList<Sugerible>();
		this.TIEMPO = tiempoDisponible;
		this.PRESUPUESTO = presupuesto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public double getPresupuesto() {
		return this.presupuesto;
	}

	public String getTipo() {
		return this.tipoDeAtraccionPreferida;
	}

	public List<Sugerible> getItinerario() {
		return this.itinerario;
	}

	public void aceptarSugerencia(Sugerible sugerencia) throws SQLException {
		this.itinerario.add(sugerencia);
		this.setTiempoDisponible(sugerencia.getTiempoTotal());
		this.setPresupuesto(sugerencia.getCosto());
		this.totalPagar += sugerencia.getCosto();
		this.totalTiempo += sugerencia.getTiempoTotal();
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, presupuesto, tiempoDisponible, tipoDeAtraccionPreferida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre) && presupuesto == other.presupuesto
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible)
				&& tipoDeAtraccionPreferida == other.tipoDeAtraccionPreferida;
	}

	public boolean puedeComprar(Sugerible sugerencia) {
		return (sugerencia.getCosto() <= this.presupuesto && sugerencia.getTiempoTotal() <= this.tiempoDisponible
				&& (!estaIncluido(sugerencia)));
	}

	private boolean estaIncluido(Sugerible buscado) {

	List<Atraccion> atracciones = new LinkedList<Atraccion>();
		for (Sugerible a : itinerario)
			if (a.esPromo())
				atracciones.addAll(a.getAtracciones());
			else
				atracciones.add((Atraccion) a);
			
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < atracciones.size()) {
			if(buscado.esPromo()) {
				for(Atraccion a : buscado.getAtracciones())
					if (atracciones.get(i).getNombre().equals(a.getNombre()))
						encontrado = true; }
			else if (atracciones.get(i).getNombre().equals(buscado.getNombre()))
				encontrado = true;
			
			i++;
		}
		return encontrado;

	}

	public void setPresupuesto(double costo) {
		this.presupuesto -= costo;
	}

	public void setTiempoDisponible(double tiempo) {
		this.tiempoDisponible -= tiempo;

	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + ", presupuesto: " + getPRESUPUESTO() + ", " + "Tiempo Disponible: "
				+ getTIEMPO() + ", Preferencia: " + tipoDeAtraccionPreferida;
	}

	public double getTIEMPO() {
		return TIEMPO;
	}

	public double getPRESUPUESTO() {
		return PRESUPUESTO;
	}

}
