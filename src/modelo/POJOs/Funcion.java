package modelo.POJOs;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import modelo.metodos.MetodosFuncion;

public class Funcion implements Cartelera {
	// TODO: Atributos
	private LocalDateTime diaYHora;
	//private Sala salaFuncion;
	//private Pelicula peliculaFuncion;
	//private Promocion promocionFuncion;
	private String salaFuncion;
	private String peliculaFuncion;
	private String promocionFuncion;

	// TODO: Contructores
	public Funcion(LocalDateTime diaYHora, String salaFuncion, String peliculaFuncion, String promocionFuncion) {
		super();
		this.diaYHora = diaYHora;
		this.salaFuncion = salaFuncion;
		this.peliculaFuncion = peliculaFuncion;
		this.promocionFuncion = promocionFuncion;
	}
	
	
	
	// TODO: Metodos Get/Set
	public LocalDateTime getDiaYHora() {
		return diaYHora;
	}

	public void setDiaYHora(LocalDateTime diaYHora) {
		this.diaYHora = diaYHora;
	}

	public String getSalaFuncion() {
		return salaFuncion;
	}

	public void setSalaFuncion(String salaFuncion) {
		this.salaFuncion = salaFuncion;
	}

	public String getPeliculaFuncion() {
		return peliculaFuncion;
	}

	public void setPeliculaFuncion(String peliculaFuncion) {
		this.peliculaFuncion = peliculaFuncion;
	}

	public String getPromocionFuncion() {
		return promocionFuncion;
	}

	public void setPromocionFuncion(String promocionFuncion) {
		this.promocionFuncion = promocionFuncion;
	}

	@Override
	public void nuevaSemana() {
		Calendar c2 = new GregorianCalendar();
		if(c2.get(Calendar.DAY_OF_WEEK)==1) {
		Iterator it = MetodosFuncion.Funciones.keySet().iterator();
		while(it.hasNext()){
		Integer key = (Integer) it.next();
		MetodosFuncion.Funciones.remove(key);
		}
		}else {
			System.out.println("Aun no es lunes");
		}
		
		
	}

}
