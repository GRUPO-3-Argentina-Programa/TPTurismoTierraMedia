package model;

import java.util.List;



public class PromocionAbs extends Promocion {

	public PromocionAbs(String valueOf, List<Atraccion> atraccionesDePromo, double precio) {
		super(valueOf, atraccionesDePromo);
		this.sumaCostos = precio;
		this.tiempoTotal = super.getTiempoTotal();
	}

	@Override
	public double getCosto() {
		return this.sumaCostos;
	}

}
