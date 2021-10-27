package model;

import java.util.*;



public class PromocionPorcentual extends Promocion {

	double porcentaje;

	public PromocionPorcentual(String tipo, List<Atraccion> atraccionesDePromo, double porcentaje, int id) {
		super(tipo, atraccionesDePromo, id);
		this.porcentaje = porcentaje;
		this.sumaCostos = this.getCosto();
		this.tiempoTotal = super.getTiempoTotal();
	}

	@Override
	public double getCosto() {
		this.sumaCostos = super.getCosto();
		return (sumaCostos * (1 - (porcentaje / 100)));
	}

}
