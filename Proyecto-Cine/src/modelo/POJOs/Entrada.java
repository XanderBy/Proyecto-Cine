package modelo.POJOs;

public class Entrada {
	private Sala sala;
	private Cine cine;
	private Promocion promocion;
	private Pelicula pelicula;
	private static Double precioEntrada;
	
	
	public Entrada(Sala sala, Cine cine, Promocion promocion, Pelicula pelicula) {
		super();
		this.sala = sala;
		this.cine = cine;
		this.promocion = promocion;
		this.pelicula = pelicula;
		this.setPrecioEntrada( (double) cine.getPrecioBase() - promocion.getDescuentoPromo());
	}


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
