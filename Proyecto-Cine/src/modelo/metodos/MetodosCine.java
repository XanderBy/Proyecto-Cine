package modelo.metodos;

import modelo.POJOs.Cine;
import modelo.POJOs.Sala;

public class MetodosCine {
	// Crear Cine
	public void crearCine(String nombreCine, String direccionCine, int telefonoConsulta, int precioBase) {
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		// Faltaria a�adir El hashmap de compa�ia
	}

	// Elimina el cine
	public void eliminarCine(String nombreCine) {
		// Aqui se eliminaria el cine del hashmap
	}

	// modificar Cine
	public void modificarCine(String nombreCine, String direccionCine, int telefonoConsulta, int precioBase) {
		// Eliminar del hashMap el cine
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		// a�adirlo Al hashmap cine
	}

	// TODO:Falta los metodos de funciones para el cine y las salas
	// A�adir Funcion al cine
	public void anadirFuncionACine() {

	}

	public void anadirSalaACine() {

	}

	// CREAR SALA

	public void crearSala(Cine c, String auditoriumCineId, int seatsNumber) {

	}

	// ELIMINAR SALA

	public void eliminarSala(Cine c, Sala s) {

	}

}
