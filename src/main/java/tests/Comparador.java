package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model. *;

public class Comparador {

	Atraccion moria;
	Atraccion mordor;
	Atraccion bosqueNegro;
	Atraccion minasTirith;
	Atraccion laComarca;
	Atraccion abismoDeHelm;
	Atraccion lothlorien;
	Atraccion erebor;
	Atraccion atraccionRegalo;
	Atraccion carretaFantasma;
	Atraccion castilloEmbrujado;
	Atraccion bosqueTenebroso;

	Promocion promoPorcentual;
	Promocion promoAxB;
	Promocion promoAbs;

	@Before
	public void setUp() {

		moria = new Atraccion("Moria", 10,"aventura", 2, 6, 1);
		mordor = new Atraccion("Mordor", 25, "Aventura",3, 4, 2);
		bosqueNegro = new Atraccion("Bosque Negro", 3,"Aventura", 4, 12, 3);
		minasTirith = new Atraccion("Minas Tirith", 5,"Paisaje", 2.5, 2, 4);
		laComarca = new Atraccion("La Comarca", 3,"Degustacion", 6.5, 150, 5);
		abismoDeHelm = new Atraccion("Abismo de Helm", 5,"Paisaje", 2, 15, 6);
		lothlorien = new Atraccion("Lothlorien", 35,"Degustacion", 1, 3, 7);
		erebor = new Atraccion("Erebor", 12,"Paisaje", 3, 32, 8);
		carretaFantasma = new Atraccion("Carreta Fantasma", 8,"Terror", 4, 25, 9);
		castilloEmbrujado = new Atraccion("Castillo Embrujado", 10,"Terror", 3.5, 4, 10);
		bosqueTenebroso = new Atraccion("Bosque Tenebroso", 6,"Terror", 4.5, 10, 11);

		List<Atraccion> atraccionesDePromo = new LinkedList<Atraccion>();
		atraccionesDePromo.add(mordor);
		atraccionesDePromo.add(bosqueNegro);
		promoPorcentual = new PromocionPorcentual("Aventura", atraccionesDePromo, 20,1);

		List<Sugerible> promoAbsoluta = new LinkedList<Sugerible>();
		promoAbsoluta.add(laComarca);
		promoAbsoluta.add(lothlorien);

	}

	@Test
	public void test() {

		List<Sugerible> listaEsperada = new LinkedList<Sugerible>();
		List<Sugerible> otraLista = new LinkedList<Sugerible>();
		// Se agregan las sugerencias esperadas de acuerdo al criterio ingresado.
		listaEsperada.add(castilloEmbrujado);
		listaEsperada.add(carretaFantasma);
		listaEsperada.add(bosqueTenebroso);
		listaEsperada.add(promoPorcentual);
		listaEsperada.add(lothlorien);
		listaEsperada.add(mordor);
		listaEsperada.add(erebor);
		listaEsperada.add(moria);
		listaEsperada.add(minasTirith);
		listaEsperada.add(abismoDeHelm);
		listaEsperada.add(laComarca);
		listaEsperada.add(bosqueNegro);

		// Lista desordenada.
		otraLista.add(abismoDeHelm);
		otraLista.add(promoPorcentual);
		otraLista.add(laComarca);
		otraLista.add(mordor);
		otraLista.add(moria);
		otraLista.add(minasTirith);
		otraLista.add(bosqueNegro);
		otraLista.add(erebor);
		otraLista.add(lothlorien);
		otraLista.add(bosqueTenebroso);
		otraLista.add(carretaFantasma);
		otraLista.add(castilloEmbrujado);
		otraLista.sort(new ComparadorDeSugerencias("Terror"));
		assertEquals(listaEsperada, otraLista);

	}

}
