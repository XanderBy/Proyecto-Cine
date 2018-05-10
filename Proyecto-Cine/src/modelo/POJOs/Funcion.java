package modelo.POJOs;

import java.time.LocalTime;

public class Funcion { //implements Cartelera
	
	private LocalTime diaYHora;
	private Sala salaFuncion;
	private Pelicula peliculaFuncion;
	private Promocion promocionFuncion;
	private int entradasVendidas;
	
	public Funcion(LocalTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion, Promocion promocionFuncion,
			int entradasVendidas) {
		super();
		this.diaYHora = diaYHora;
		this.salaFuncion = salaFuncion;
		this.peliculaFuncion = peliculaFuncion;
		this.promocionFuncion = promocionFuncion;
		this.entradasVendidas = entradasVendidas;
	}
	public LocalTime getDiaYHora() {
		return diaYHora;
	}
	public void setDiaYHora(LocalTime diaYHora) {
		this.diaYHora = diaYHora;
	}
	public Sala getSalaFuncion() {
		return salaFuncion;
	}
	public void setSalaFuncion(Sala salaFuncion) {
		this.salaFuncion = salaFuncion;
	}
	public Pelicula getPeliculaFuncion() {
		return peliculaFuncion;
	}
	public void setPeliculaFuncion(Pelicula peliculaFuncion) {
		this.peliculaFuncion = peliculaFuncion;
	}
	public Promocion getPromocionFuncion() {
		return promocionFuncion;
	}
	public void setPromocionFuncion(Promocion promocionFuncion) {
		this.promocionFuncion = promocionFuncion;
	}
	public int getEntradasVendidas() {
		return entradasVendidas;
	}
	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}
}
