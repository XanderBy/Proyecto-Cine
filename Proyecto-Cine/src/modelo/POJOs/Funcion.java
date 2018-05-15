package modelo.POJOs;

import java.time.LocalTime;

public class Funcion implements Cartelera {
	// TODO: Atributos
	private LocalTime diaYHora;
	private Sala salaFuncion;
	private Pelicula peliculaFuncion;
	private Promocion promocionFuncion;
	

	// TODO: Contructores
	public Funcion(LocalTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion, Promocion promocionFuncion) {
		super();
		this.diaYHora = diaYHora;
		this.salaFuncion = salaFuncion;
		this.peliculaFuncion = peliculaFuncion;
		this.promocionFuncion = promocionFuncion;
	}
	
	
	
	// TODO: Metodos Get/Set
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



	@Override
	public void nuevaSemana() {
		// TODO Auto-generated method stub
		
	}

}
