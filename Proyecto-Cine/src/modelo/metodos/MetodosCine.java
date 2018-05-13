package modelo.metodos;

import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;

public class MetodosCine {
	// Crear Cine
	public void crearCine(String nombreCine, String direccionCine, int telefonoConsulta, int precioBase) {
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		Compagnia.listaCines.put(nombreCine, cine);
	}

	// Elimina el cine
	public void eliminarCine(String nombreCine) {
		Compagnia.listaCines.remove(nombreCine);
	}

	// modificar Cine
	public void modificarCine(String nombreCineAntiguo,String nombreCine, String direccionCine, int telefonoConsulta, int precioBase) {
		Compagnia.listaCines.remove(nombreCineAntiguo);
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		Compagnia.listaCines.put(nombreCine, cine);
	}


	

}