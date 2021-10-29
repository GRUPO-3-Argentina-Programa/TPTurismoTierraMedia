package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.*;

import org.junit.*;

import model. *;


public class UsuariosTest {

	private List<Usuario> usuarios;
	private Usuario u1;
	private Sugerible s1;
	private Sugerible s2;
	private Sugerible s3;

	@Before
	public void setUp() {
		usuarios = new LinkedList<Usuario>();
		
		u1 = new Usuario("u1", 100, 80, "Aventura");

		s1 = new Atraccion("Moria", 10,"Aventura", 2, 6, 1);
		s2 = new Atraccion("Moria", 80,"Aventura", 81, 6, 2);
		s3 = new Atraccion("Moria", 101,"Aventura", 2, 6, 3);
	}

	@Test
	public void queAceptaSugerencia() throws SQLException {

		u1.aceptarSugerencia(s1);

		assertEquals(90, u1.getPresupuesto(), 0.01);
		assertEquals(78, u1.getTiempoDisponible(), 0.01);
	}

	@Test
	public void quePuedeComprar() {

		assertTrue(u1.puedeComprar(s1));
		assertFalse(u1.puedeComprar(s2));
		assertFalse(u1.puedeComprar(s3));

	}

}
