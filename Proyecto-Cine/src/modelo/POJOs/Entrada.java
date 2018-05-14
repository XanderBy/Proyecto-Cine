package modelo.POJOs;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Entrada {
	// TODO: Atributos
	private Sala sala;
	private Cine cine;
	private Promocion promocion;
	private Pelicula pelicula;
	private static Double precioEntrada;
	public HashMap<Cine, Integer> EntradasVendidasCine = new HashMap<Cine, Integer>();

	// TODO: Constructores
	public Entrada(Sala sala, Cine cine, Promocion promocion, Pelicula pelicula) {
		super();
		this.sala = sala;
		this.cine = cine;
		this.promocion = promocion;
		this.pelicula = pelicula;
		this.setPrecioEntrada((double) cine.getPrecioBase() - promocion.getDescuentoPromo());
		aumentarEntradaVendida(cine);
	}

	// TODO: metodos simples
	public void aumentarEntradaVendida(Cine cine) {
		if (EntradasVendidasCine.containsKey(cine)) {
			EntradasVendidasCine.remove(cine);
			volverCeroEntradaVendidas(cine);
			cine.setEntradasVendidas(cine.getEntradasVendidas()+1);
			EntradasVendidasCine.put(cine, cine.getEntradasVendidas());
		} else {
			volverCeroEntradaVendidas(cine);
			cine.setEntradasVendidas(cine.getEntradasVendidas() + 1);
			EntradasVendidasCine.put(cine, cine.getEntradasVendidas());
		}
	}
	//metodo volver ha cero entradas vendidas cada principio de mes
	public void volverCeroEntradaVendidas(Cine cine) {
		Calendar c2 = new GregorianCalendar();
		if(c2.get(Calendar.DATE)==1) {
			cine.setEntradasVendidas(0);
		}else {
			System.out.println("Aun no es la fecha");
		}
	}
	
	// TODO: Metodos Get/Set
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public static Double getPrecioEntrada() {
		return precioEntrada;
	}

	public static void setPrecioEntrada(Double precioEntrada) {
		Entrada.precioEntrada = precioEntrada;
	}

}
