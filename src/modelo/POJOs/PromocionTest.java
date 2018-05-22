package modelo.POJOs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class PromocionTest {
	
	Promocion p, p2;
	
	@Before
	final void before() {
		p=new Promocion("Menores de 12",15);
		p2=new Promocion("Menores de 12",0);
	}

	@Test
	final void testGetDescripcionPromo() {
		before();
		String resultado=p.getDescripcionPromo();
		String esperado="Menores de 12";
		assertEquals(esperado,resultado);
	}

	@Test
	final void testSetDescripcionPromo() {
		before();
		p2.setDescripcionPromo("Mayores de 75");
		String resultado=p2.getDescripcionPromo();
		String esperado="Mayores de 75";
		assertEquals(esperado, resultado);
	}

	@Test
	final void testGetDescuentoPromo() {
		before();
		int resultado=p.getDescuentoPromo();
		int esperado=15;
		assertEquals(esperado, resultado);
	}

	@Test
	final void testSetDescuentoPromo() {
		before();
		p2.setDescuentoPromo(10);
		int resultado=p2.getDescuentoPromo();
		int esperado=10;
		assertEquals(esperado, resultado);
	}

	@Test
	final void testToString() {
		before();
		String resultado=p2.toString();
		String esperado="Descripcion de la promocion: ".concat("Menores de 12") + "\nDescuento aplicable: "+ 0;
		assertEquals(esperado, resultado);
	}

}
