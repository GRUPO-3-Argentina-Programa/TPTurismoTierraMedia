package model;

import java.util.List;



public interface Sugerible {

	public String getNombre();

	public double getCosto();

	public double getTiempoTotal();

	public String getTipoAtraccion();

	public boolean hayCupo();

	public boolean esPromo();

	public void restarCupo();

	public List<Atraccion> getAtracciones();

	public String[] getNombresDeAtracciones();
	
	public int getId();

}
