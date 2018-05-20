package modelo.metodos;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import modelo.POJOs.Sala;

import javax.swing.JOptionPane;

import controlador.ConexionManager;
import modelo.POJOs.Cine;
import modelo.POJOs.Promocion;

public class MetodosSala {

	// DECLARACION:Map (Key: IdSalaCine, Value: Sala)

	public static HashMap<String, Sala> salas = new HashMap<String, Sala>();

	// CONSTRUCTOR:Vacio

	public MetodosSala() {

	}
	
	public void cargarSalas() throws SQLException {
		// 1.Declaramos map y variables
		HashMap<String, Sala> loadHalls = new HashMap<String, Sala>();
		
		String idHallCine,nameHall;
		int seatsNumber;
		Sala s;
		// 2. Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos el
		// metodo crear()
		ConexionManager conexionManager = new ConexionManager();
		Connection conexion = conexionManager.crear();

		try {

			// 2.1.Creamos statement
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT IDSALACINE,NOMBRESALA,NUMEROBUTACAS FROM SALA");
			// 2.2.Preparamos el ResultSet
			ResultSet resultado = consulta.executeQuery();
			// 2.3.Iteramos sobre las tuplas de la base de datos
			while (resultado.next()) {
				idHallCine = resultado.getString("IDSALACINE");
				nameHall = resultado.getString("NOMBRESALA");
				seatsNumber= resultado.getInt("NUMEROBUTACAS");
				s = new Sala(idHallCine, seatsNumber);
				loadHalls.put(idHallCine, s);
			}

		} catch (Exception e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}

		// 3.Actualizamos map
		salas = loadHalls;
		// 4.Cerramos la conexion
		conexionManager.cerrar();
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
