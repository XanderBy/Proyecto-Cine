package modelo.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.POJOs.Cine;
import modelo.POJOs.Sala;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.ConexionManager;


public class MetodosSala {

	/**
	 * Declaramos map en el que se van a cargar las salas creadas sin relacionar con los cines
	 */
	public static HashMap<String, Sala> salas = new HashMap<String, Sala>();

	/**
	 * Constructor vacio
	 */
	public MetodosSala() {

	}

	/**
	 * Carga las salas existentes en el map salas y salasCine
	 * 
	 * @throws SQLException
	 */
	public void cargarSalas() throws SQLException {
		// 1.Variables
		Cine c = new Cine();// Para acceder a salasCine
		String idSalCine, nombreSal;
		int numButacas;
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
				idSalCine = resultado.getString("IDSALACINE").concat(resultado.getString("NOMBRESALA"));
				nombreSal = resultado.getString("NOMBRESALA");
				numButacas = resultado.getInt("NUMEROBUTACAS");
				s = new Sala(idSalCine, numButacas);
				s.setIdSalaCine(idSalCine);
				salas.put(idSalCine, s);
				c.salasCine.put(idSalCine, s);
			}

		} catch (Exception e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}

		// 3.Cerramos la conexion
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
		PreparedStatement consulta = conexion.prepareStatement("SELECT IDSALACINE FROM SALA");
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
	 * Crea una sala si no hay otra en la BBDD con la misma PK
	 * 
	 * @param auditoriumCineId
	 * @param seatsNumber
	 * @throws SQLException
	 */
	public void aniadirSala(String nombreCin, String nombreSal, int seatsNumber) throws SQLException {
		
		//0.Declaramos valor idSalaCine
		String idSalCine=nombreCin.concat(nombreSal);
		
		// 1.Cargamos salas en el map
		cargarSalas();

		// 2.Obtenemos claves primarias
		ArrayList<String> clavesPrimariasSalas = obtenerClavesPrimariasSala();

		// 3.Comprobamos que auditorimCineId no coincida con ninguna clave primaria e
		// insertamos

		try {
			if (nombreSal==null || seatsNumber == 0) {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos. Recuerde que debe introducir el nombre del cine al que desea aniadir una sala");
			} else if (clavesPrimariasSalas.contains(idSalCine)) {
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
				preparedStatement.setString(1, idSalCine);
				// 2.2.3.Decimos que en el valor desconocido 2 inserte el valor del String
				// auditoriumCineId
				preparedStatement.setString(2, nombreSal);
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
	
	/**
	 * Modifica la sala si existe en la BBDD
	 * @param oldAuditoriumCineId
	 * @param auditoriumCineId
	 * @param seatsNumber
	 * @throws SQLException
	 */
	public void modificarSala(String oldNombreSala, String nombreCin, String nombreSal, int seatsNumber)
			throws SQLException {
		//0.Declaramos valor idSalaCine
		String idSalCine=nombreCin.concat(nombreSal);
		String oldIdSalaCine=nombreCin.concat(oldNombreSala);

		// 1.Cargamos salas en el map y generamos la tabla
		cargarSalas();
		generarTablaSalas();

		// 2.Comprobamos que no haya valores nulos
		if (oldNombreSala == null || nombreSal == null || seatsNumber == 0 || idSalCine==null || oldIdSalaCine==null) {
			JOptionPane.showMessageDialog(null, "Debe introducir valores validos");
		} else {
			if (salas.containsKey(oldIdSalaCine)) {
				// 2.1 Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos
				// el metodo crear()
				ConexionManager conexionManager = new ConexionManager();
				Connection conexion = conexionManager.crear();

				// 2.2 Realizamos la actualizacion
				// 2.2.1.Creamos el PreparedStatement: Update
				try {
					PreparedStatement preparedStatement = conexion.prepareStatement(
							"UPDATE SALA SET IDSALACINE=?, NOMBRESALA=?, NUMEROBUTACAS=? WHERE IDSALACINE="
									+ oldIdSalaCine);
					// 2.2.2.Decimos que en el valor desconocido 1 inserte el valor del String
					// auditoriumCode
					preparedStatement.setString(1, idSalCine);
					// 2.2.3.Decimos que en el valor desconocido 2 inserte el valor del String
					// auditoriumCineId
					preparedStatement.setString(2, nombreSal);
					// 2.2.4.Decimos que en el valor desconocido 2 inserte el valor del int
					// auditoriumCineId
					preparedStatement.setInt(3, seatsNumber);
					// 2.2.5.Ejecutamos el preparedStatement
					preparedStatement.execute();
					// 2.2.6.Informamos
					JOptionPane.showMessageDialog(null, "Promocion modificada correctamente");
					// 2.2.7. Actualizamos map
					cargarSalas();
					// 2.2.7.Cerramos la conexion
					conexionManager.cerrar();
				} catch (Exception e) {
					System.err.println("Excepcion no controlada");
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "La sala que busca ya no existe");
			}

		}

	}
	

	/**
	 * Prepara una tabla para refrescar la contenida en modificar cine (salas cine)
	 * 
	 * @return tabla
	 * @throws SQLException
	 */
	public DefaultTableModel generarTablaSalas() {
		// 1.Declaramos defaultTableModel,matriz tipo Object y string
		DefaultTableModel tablaSala = new DefaultTableModel();
		Object[][] resultado;
		String[] columnNames = { "Id Sala", "Nombre sala", "Numero butacas" };
		// 2.Abrimos la conexion
		try {
			ConexionManager conexionManager = new ConexionManager();
			Connection conexion = conexionManager.crear();
			// 3.Creamos el prepared statement que nos devolvera el numero de tuplas
			PreparedStatement count = conexion.prepareStatement("SELECT COUNT(*) AS NUM_SALAS FROM SALA");
			ResultSet rs1 = count.executeQuery();
			rs1.next();// (*) Aqui saltaba la excepcion
			int numeroFilas = rs1.getInt("NUM_SALAS");
			rs1.close();
			// 4.Establecemos que la matriz sera del tamanio [numerofilas][2] donde 2 es el
			// numero de columnas.
			resultado = new Object[numeroFilas][3];

			// 5.Creamos el prepared statement que obtendra toda la info de la tabla
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM SALA");
			ResultSet rs2 = consulta.executeQuery();
			// 6.Obtenemos valores por tuplas
			int i = 0;
			while (rs2.next()) {
				String idSalaCine = rs2.getString("IDSALACINE");
				String nombreSala = rs2.getString("NOMBRESALA");
				int numeroButacas = rs2.getInt("NUMEROBUTACAS");
				resultado[i][0] = idSalaCine;
				resultado[i][1] = nombreSala;
				resultado[i][2] = numeroButacas;
				i++;
			}
			// 7.Cerramos la conexion
			conexionManager.cerrar();
			// 8.Creamos la tabla
			tablaSala.setDataVector(resultado, columnNames);
			return tablaSala;
		} catch (SQLException e) {
			System.err.println("Excepcion SQL no controlada");
			e.printStackTrace();
			return null;
		}
	}

}
