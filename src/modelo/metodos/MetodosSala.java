package modelo.metodos;

import java.awt.HeadlessException;
import java.util.HashMap;
import modelo.POJOs.Sala;

import javax.swing.JOptionPane;

import modelo.POJOs.Cine;

public class MetodosSala {

	// DECLARACION:Map (Key: IdSalaCine, Value: Sala)

	public static HashMap<String, Sala> salas = new HashMap<String, Sala>();

	// CONSTRUCTOR:Vacio

	public MetodosSala() {

	}

	// METODO: Crear salas recibiendo por parametro su nombre y numero de asientos. Introduce la sala en el map.

	public Sala crearSala(String auditoriumCineId, int seatsNumber) {
		Sala s = null;
		try {
			if (auditoriumCineId == null) {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
			} else {
				s = new Sala(auditoriumCineId, seatsNumber);
				salas.put(auditoriumCineId, s);
				JOptionPane.showMessageDialog(null, "Sala creada correctamente");
			}
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
		return s;
	}
	
	//METODO: Comprueba si existe la sala en el map listaSalas de la clase Cine. Si existe la elimina, sino informa.
	
	public void eliminarSala(Cine c, String auditoriumCineId) {
		
		try {
			if(c==null) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un cine");
			}else {
				if (c.salasCine.containsKey(auditoriumCineId)) {
					c.salasCine.remove(auditoriumCineId);
					JOptionPane.showMessageDialog(null, "Sala eliminada correctamente");
				} else {
					JOptionPane.showMessageDialog(null, "La sala que intenta eliminar ya no existe");
				}
			}
		} catch (HeadlessException e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}
		

	}
	
	//TODO: Implementar

	public void modificarSala() {

	}

}
