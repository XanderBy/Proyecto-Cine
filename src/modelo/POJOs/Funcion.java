package modelo.POJOs;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import modelo.metodos.MetodosFuncion;

public class Funcion implements Cartelera, Runnable {
	// TODO: Atributos
	private Thread thread;
	private LocalDateTime diaYHora;
	private Sala salaFuncion;
	private Pelicula peliculaFuncion;
	private Promocion promocionFuncion;
	private boolean funcionando;
	private int contador = 0;

	// TODO: Contructores
	public Funcion() {

	}

	public Funcion(LocalDateTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion, Promocion promocionFuncion) {
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

	// ---------------------------------------------------------
	public void start() {
		thread = new Thread((Runnable) this);
		thread.start();
		funcionando = true;
	}

	// ---------------------------------------------------------
	@Override
	public void nuevaSemana() {
		Calendar c2 = new GregorianCalendar();
		if (c2.get(Calendar.DAY_OF_WEEK) == 2 && contador == 0) {
			contador++;
			Iterator it = MetodosFuncion.Funciones.keySet().iterator();
			while (it.hasNext()) {
				Integer key = (Integer) it.next();
				MetodosFuncion.Funciones.remove(key);
			}
		} else {
			if (c2.get(Calendar.DAY_OF_WEEK) == 3) {
				contador = 0;
			}
			// System.out.println("Aun no es lunes");
		}

	}

	public void stop() {
		try {
			thread.join();
			funcionando = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (funcionando) {

			nuevaSemana();
		}
		stop();
	}

}
