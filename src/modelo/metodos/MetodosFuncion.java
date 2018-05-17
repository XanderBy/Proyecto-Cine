package modelo.metodos;

import java.time.LocalTime;
import java.util.HashMap;

import controlador.Conexion;
import javax.swing.JOptionPane;
import modelo.POJOs.Compagnia;
import modelo.POJOs.Funcion;
import modelo.POJOs.Pelicula;
import modelo.POJOs.Promocion;
import modelo.POJOs.Sala;

public class MetodosFuncion extends Conexion {
	public static HashMap<LocalTime, Funcion> Funciones = new HashMap<LocalTime, Funcion>();

	public void crearFuncion(LocalTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion,
			Promocion promocionFuncion) {
            if(diaYHora==null || salaFuncion==null|| peliculaFuncion==null|| promocionFuncion==null){
               JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
            }else{
		Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
		Funciones.put(diaYHora, funcion);
                JOptionPane.showMessageDialog(null, "Funcion Añadida");
            }
	}

	public void eliminarFuncion(LocalTime diaYHora) {
		Funciones.remove(diaYHora);
                JOptionPane.showMessageDialog(null, "Funcion Eliminada");
	}

	public void modificarFuncion(LocalTime diaYHoraAntiguo, LocalTime diaYHora, Sala salaFuncion,
			Pelicula peliculaFuncion, Promocion promocionFuncion) {
            if(diaYHoraAntiguo==null|| diaYHora== null|| salaFuncion==null|| peliculaFuncion==null||promocionFuncion==null){
                JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
            }else{
		Funciones.remove(diaYHoraAntiguo);
		Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
		Funciones.put(diaYHora, funcion);
                JOptionPane.showMessageDialog(null, "Funcion modificada");
            }
	}

	public void anadirFuncionACine(String nombreCine, LocalTime diaYHora) {
		Compagnia.listaCines.get(nombreCine).funcionesSemana.put(diaYHora, Funciones.get(diaYHora));
	}

	public void eliminarFuncionBBDD() {

	}

	public void insertarFuncionBBDD() {

	}

	public void actualizarFuncionBBDD() {

	}

	public void cogerFuncionBBDD() {

	}

}
