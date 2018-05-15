package modelo.metodos;

import controlador.Conexion;
import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;

public class MetodosCine extends Conexion{
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
	public void modificarCine(String nombreCineAntiguo, String nombreCine, String direccionCine, int telefonoConsulta,
			int precioBase) {
		Compagnia.listaCines.remove(nombreCineAntiguo);
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		Compagnia.listaCines.put(nombreCine, cine);
	}

	public void eliminarCineBBDD() {

	}

	public void insertarCineBBDD() {

	}

	public void actualizarCineBBDD() {

	}
	public void cogerCineBBDD() {
		
	}

}