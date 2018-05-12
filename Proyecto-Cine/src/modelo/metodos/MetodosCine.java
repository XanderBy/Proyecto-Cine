package modelo.metodos;

import modelo.POJOs.Cine;

public class MetodosCine {
	// Crear Cine
	public void crearCine(String nombreCine, String direccionCine, int telefonoConsulta, int precioBase) {
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		// Faltaria añadir El hashmap de compañia
	}

	// Elimina el cine
	public void eliminarCine(String nombreCine) {
		// Aqui se eliminaria el cine del hashmap
	}

	// modificar Cine
	public void modificarCine(String nombreCine, String direccionCine, int telefonoConsulta, int precioBase) {
		// Eliminar del hashMap el cine
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		// añadirlo Al hashmap cine
	}

	// TODO:Falta los metodos de funciones para el cine y las salas
	// Añadir Funcion al cine
	public void anadirFuncionACine() {

	}

	public void anadirSalaACine() {

	}

	

}