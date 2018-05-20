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
	 * El valor nameHall (codigoSala) esta anulado (vale lo mismo que idSalaCine)
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
	 * @return ArrayList<String>: Claves primarias sala
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

	/**
	 * Crea una sala si no hay otra en la BBDD con el mismo nombre
	 * @param auditoriumCineId
	 * @param seatsNumber
	 * @throws SQLException
	 */
	public void crearSala(String auditoriumCineId, int seatsNumber) throws SQLException {
		//0.Igualamos la clave primaria al valor de auditoriumCineId
		String auditoriumCode=auditoriumCineId;
		
		//1.Cargamos salas en el map
		cargarSalas();
		
		// 2.Obtenemos claves primarias
		ArrayList<String> clavesPrimariasSalas= obtenerClavesPrimariasSala();
		
		//3.Comprobamos que auditorimCineId no coincida con ninguna clave primaria e insertamos
		
		try {
			if (auditoriumCineId == null || seatsNumber==0 || auditoriumCode==null) {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
			} else if (clavesPrimariasSalas.contains(auditoriumCode)) {
				JOptionPane.showMessageDialog(null, "Ya existe una sala con ese nombre");
			} else {
				// 2.1 Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos
				// el metodo crear()
				ConexionManager conexionManager = new ConexionManager();
				Connection conexion = conexionManager.crear();

				// 2.2 Realizamos la insercion
				// 2.2.1.Creamos el PreparedStatement: Consulta, con valores desconocidos
				PreparedStatement preparedStatement = conexion
						.prepareStatement("INSERT INTO SALA (IDSALACINE, NOMBRESALA, NUMEROBUTACAS) VALUES (?, ?, ?)");
				// 2.2.2.Decimos que en el valor desconocido 1 inserte el valor del String
				// auditoriumCode
				preparedStatement.setString(1, auditoriumCode);
				// 2.2.3.Decimos que en el valor desconocido 2 inserte el valor del String
				// auditoriumCineId
				preparedStatement.setString(2, auditoriumCineId);
				// 2.2.4.Decimos que en el valor desconocido 2 inserte el valor del String
				// seatsNumber
				preparedStatement.setInt(3, seatsNumber);
				// 2.2.5.Ejecutamos el preparedStatement
				preparedStatement.execute();
				// 2.2.6.Cerramos la conexion
				conexionManager.cerrar();
				// 2.2.7.Informamos
				JOptionPane.showMessageDialog(null, "Sala creada correctamente");
				// 2.2.8. Actualizamos map
				cargarSalas();
			}
		} catch (Exception e) {
			System.err.println("Excepcion no controlada al crear sala");
			e.printStackTrace();
		}
	}
	
	public void modificarSala() {

	}
	
	//METODO: Comprueba si existe la sala en el map listaSalas de la clase Cine. Si existe la elimina, sino informa.
	
	public void eliminarSala(Cine c, String auditoriumCineId) {
	
	}



}
