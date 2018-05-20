package modelo.metodos;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import modelo.POJOs.Sala;

import javax.swing.JOptionPane;

import controlador.ConexionManager;
import modelo.POJOs.Cine;
import modelo.POJOs.Promocion;

public class MetodosSala {

	/**
	 * Declaramos map en el que se van a cargar las salas creadas
	 */
	public static HashMap<String, Sala> salas = new HashMap<String, Sala>();

	
	/**
	 * Constructor vacio
	 */
	public MetodosSala() {

	}
	
	/**
	 * Carga las salas existentes en el map
	 * El valor nameHall (codigoSala) vesta anulado (vale lo mismo que idSalaCine)
	 * @throws SQLException
	 */
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
	
	/**
	 * 
	 * @return ArrayList<Integer>: Claves primarias promocion
	 * @throws SQLException
	 */
	public ArrayList<String> obtenerClavesPrimariasSala() throws SQLException {

		// 1.Declaramos ArrayList
		ArrayList<String> clavesPrimariasSala = new ArrayList<String>();

		// 2. Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos el
		// metodo crear()
		ConexionManager conexionManager = new ConexionManager();
		Connection conexion = conexionManager.crear();

		// 2.1.Creamos el Statement
		PreparedStatement consulta = conexion.prepareStatement("SELECT IDSALACINE FROM PROMOCION");
		// 2.2.Preparamos el ResultSet
		ResultSet resultado = consulta.executeQuery();
		// 2.3.Iteramos sobre las tuplas de la base de datos
		while (resultado.next()) {
			clavesPrimariasSala.add(resultado.getString("IDSALACINE"));
		}
		// 2.4.Cerramos la conexion
		conexionManager.cerrar();

		// 2.5.Devolvemos claves primarias promocion
		return clavesPrimariasSala;
	}

	// METODO: Crear salas recibiendo por parametro su nombre y numero de asientos. Introduce la sala en el map.

	public Sala crearSala(String auditoriumCineId, int seatsNumber) {//TODO: Estoy aqui
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
