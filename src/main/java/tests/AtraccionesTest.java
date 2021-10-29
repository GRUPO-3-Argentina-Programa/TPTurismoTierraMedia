package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model. *;

public class AtraccionesTest {
	Atraccion Moria;
	List<Atraccion> atracciones;

	@Test
	public void constructorDeAtraccionesTest() {
		Moria = new Atraccion("Moria", 10,"aventura", 2, 6, 1);

		assertNotNull(Moria);
	}

	@Test
	public void queLasCreaCorrectamenteTest() {
		Moria = new Atraccion("Moria", 10,"Aventura", 2, 6, 1);

		assertEquals("Moria", Moria.getNombre());
		assertEquals(10, Moria.getCosto(), 0);
		assertEquals(2, Moria.getTiempoTotal(), 0);
		assertEquals(6, Moria.getCupo(), 0);
		assertEquals("Aventura", Moria.getTipoAtraccion());
	}

	@Test
	public void hayCupoTest() {
		Moria = new Atraccion("Moria", 10,"aventura", 2, 1, 1);

		assertTrue(Moria.hayCupo());

		Moria.restarCupo();

		assertFalse(Moria.hayCupo());
	}

}
