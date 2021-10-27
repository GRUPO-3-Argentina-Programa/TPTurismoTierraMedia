package model;

import java.util.List;



public class PromocionAbs extends Promocion {

	public PromocionAbs(String tipo, List<Atraccion> atraccionesDePromo, double precio) {
		super(tipo, atraccionesDePromo);
		this.sumaCostos = precio;
		this.tiempoTotal = super.getTiempoTotal();
	}

	@Override
	public double getCosto() {
		return this.sumaCostos;
	}

}
