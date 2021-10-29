package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import model. *;


public class PromocionTest {

	Atraccion moria;
	Atraccion mordor;
	Atraccion bosqueNegro;
	Atraccion minasTirith;
	Atraccion laComarca;
	Atraccion abismoDeHelm;
	Atraccion Lothlorien;
	Atraccion erebor;
	Atraccion atraccionRegalo;

	List<Atraccion> atracciones;
	List<Atraccion> atraccionesAxB;
	List<Atraccion> atraccionesAbs;

	@Before
	public void crearListaPromociones() {
		Atraccion moria = new Atraccion("Moria", 10,"aventura", 2, 6, 1);
		Atraccion mordor = new Atraccion("Mordor", 25, "Aventura",3, 4, 2);
		Atraccion bosqueNegro = new Atraccion("Bosque Negro", 3,"Aventura", 4, 12, 3);
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5,"Paisaje", 2.5, 2, 4);
		Atraccion laComarca = new Atraccion("La Comarca", 3,"Degustacion", 6.5, 150, 5);
		Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 5,"Paisaje", 2, 15, 6);
		Atraccion lothlorien = new Atraccion("Lothlorien", 35,"Degustacion", 1, 3, 7);

		atracciones = new LinkedList<Atraccion>();
		atracciones.add(mordor);
		atracciones.add(moria);
		atracciones.add(bosqueNegro);

		atraccionesAxB = new LinkedList<Atraccion>();
		atraccionesAxB.add(minasTirith);
		atraccionesAxB.add(abismoDeHelm);

		atraccionesAbs = new LinkedList<Atraccion>();
		atraccionesAbs.add(laComarca);
		atraccionesAbs.add(lothlorien);
	}

	@Test
	public void promosProcentualesTest() {

		Promocion promo = new PromocionPorcentual("Aventura", atracciones, 20,1);

		assertEquals(30.4, promo.getCosto(), 0.001);
		assertEquals("Aventura", promo.getTipoAtraccion());
		assertEquals(9, promo.getTiempoTotal(), 0.001);

		assertTrue(promo.esPromo());
		assertTrue(promo.hayCupo());
	}

	@Test
	public void restaCupoProcentualesTest() {
		Promocion promo = new PromocionPorcentual("Aventura", atracciones, 20,1);

		promo.restarCupo();
		promo.restarCupo();
		promo.restarCupo();

		assertTrue(promo.hayCupo());

	}

	@Test
	public void promosAxBTest() {
		Atraccion erebor = new Atraccion("Erebor", 12,"Paisaje", 3, 32, 8);
		Atraccion atraccionRegalo = erebor;

		Promocion promoAxB = new PromocionAxB("Paisaje", atraccionesAxB, atraccionRegalo, 2);
		assertEquals(10, promoAxB.getCosto(), 0.001);
		assertEquals("Paisaje", promoAxB.getTipoAtraccion());
		assertEquals(7.5, promoAxB.getTiempoTotal(), 0.001);

		assertTrue(promoAxB.esPromo());
		assertTrue(promoAxB.hayCupo());
	}

	@Test
	public void restaCupoAxBTest() {
		Atraccion minasTirith = new Atraccion("Minas Tirith", 5,"Paisaje", 2.5, 2, 4);

		Atraccion erebor = new Atraccion("Erebor", 12,"Paisaje", 3, 32, 8);
		Atraccion atraccionRegalo = erebor;

		Promocion promoAxB = new PromocionAxB("Paisaje", atraccionesAxB, atraccionRegalo, 2);

		promoAxB.restarCupo();
		promoAxB.restarCupo();
		promoAxB.restarCupo();

		minasTirith.restarCupo();

		assertEquals(1, minasTirith.getCupo());

		erebor.restarCupo();

		assertEquals(28, erebor.getCupo());

	}

	@Test
	public void promosAbsTest() {

		Promocion promoAbs = new PromocionAbs("Degustacion", atraccionesAbs, 36, 3);
		assertEquals(36, promoAbs.getCosto(), 0.001);
		assertEquals("Degustacion", promoAbs.getTipoAtraccion());
		assertEquals(7.5, promoAbs.getTiempoTotal(), 0.001);

		assertTrue(promoAbs.esPromo());
		assertTrue(promoAbs.hayCupo());
	}

	@Test
	public void restaCupoAbsTest() {
		Promocion promoAbs = new PromocionAbs("Degustacion", atraccionesAbs, 36, 3);

		promoAbs.restarCupo();
		promoAbs.restarCupo();

		assertTrue(promoAbs.hayCupo());

	}

}
