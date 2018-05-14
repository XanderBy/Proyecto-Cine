package modelo.metodos;

import java.time.LocalTime;
import java.util.HashMap;

import modelo.POJOs.Compagnia;
import modelo.POJOs.Funcion;
import modelo.POJOs.Pelicula;
import modelo.POJOs.Promocion;
import modelo.POJOs.Sala;

public class MetodosFuncion {
	HashMap<LocalTime, Funcion> Funciones = new HashMap<LocalTime, Funcion>();

	public void crearFuncion(LocalTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion,
			Promocion promocionFuncion) {
		Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
		Funciones.put(diaYHora, funcion);
	}

	public void eliminarFuncion(LocalTime diaYHora) {
		Funciones.remove(diaYHora);
	}

	public void modificarFuncion(LocalTime diaYHoraAntiguo, LocalTime diaYHora, Sala salaFuncion,
			Pelicula peliculaFuncion, Promocion promocionFuncion) {
		Funciones.remove(diaYHoraAntiguo);
		Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
		Funciones.put(diaYHora, funcion);
	}

	public void anadirFuncionACine(String nombreCine, LocalTime diaYHora) {
		Compagnia.listaCines.get(nombreCine).funcionesSemana.put(diaYHora, Funciones.get(diaYHora));
	}

	

}
