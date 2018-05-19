package modelo.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.ConexionManager;
import modelo.POJOs.Promocion;

public class MetodosPromocion {

	/**
	 * Declaramos map en el que se van a cargar las promociones creadas
	 */
	public static HashMap<Integer, Promocion> mapPromocionesCreadas = new HashMap<Integer, Promocion>();

	/**
	 * Constructor vacio
	 */
	public MetodosPromocion() {

	}

	/**
	 * Carga las promociones existentes en la BBDD en el map
	 * 
	 * @throws SQLException
	 */
	public void cargarPromociones() throws SQLException {
		// 1.Declaramos map y variables
		HashMap<Integer, Promocion> loadPromotions = new HashMap<Integer, Promocion>();
		int descuPromo;
		String descrPromo;
		Promocion p;

		try {
			// 2. Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos el
			// metodo crear()
			ConexionManager conexionManager = new ConexionManager();
			Connection conexion = conexionManager.crear();

			// 2.1.Creamos statement
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT DESCUENTOPROMO,DESCRIPCIONPROMO FROM PROMOCION");
			// 2.2.Preparamos el ResultSet
			ResultSet resultado = consulta.executeQuery();
			// 2.3.Iteramos sobre las tuplas de la base de datos
			while (resultado.next()) {
				descuPromo = resultado.getInt("DESCUENTOPROMO");
				descrPromo = resultado.getString("DESCRIPCIONPROMO");
				p = new Promocion(descrPromo, descuPromo);
				loadPromotions.put(descuPromo, p);
			}
			// 2.4.Cerramos la conexion
			conexionManager.cerrar();
		} catch (Exception e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}

		// 3.Actualizamos map
		mapPromocionesCreadas = loadPromotions;
	}

	/**
	 * 
	 * @return ArrayList<Integer>: Claves primarias promocion
	 * @throws SQLException
	 */
	public ArrayList<Integer> obtenerClavesPrimariasPromocion() throws SQLException {

		// 1.Declaramos ArrayList
		ArrayList<Integer> clavesPrimariasPromocion = new ArrayList<Integer>();

		// 2. Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos el
		// metodo crear()
		ConexionManager conexionManager = new ConexionManager();
		Connection conexion = conexionManager.crear();

		// 2.1.Creamos el Statement
		PreparedStatement consulta = conexion.prepareStatement("SELECT DESCUENTOPROMO FROM PROMOCION");
		// 2.2.Preparamos el ResultSet
		ResultSet resultado = consulta.executeQuery();
		// 2.3.Iteramos sobre las tuplas de la base de datos
		while (resultado.next()) {
			clavesPrimariasPromocion.add(resultado.getInt("DESCUENTOPROMO"));
		}
		// 2.4.Cerramos la conexion
		conexionManager.cerrar();

		// 2.5.Devolvemos claves primarias promocion
		return clavesPrimariasPromocion;
	}

	/**
	 * Invoca obtenerClavesPrimariasPromocion() Si el promoDiscount introducido no
	 * coincide con ninguna PK inserta en la BBDD Invoca cargarPromociones()
	 * 
	 * @param promoDiscount
	 * @param promoDescription
	 * @throws SQLException
	 */
	public void crearPromocion(int promoDiscount, String promoDescription) throws SQLException {
		// 0.Cargamos promociones en el map
		cargarPromociones();

		// 1.Obtenemos claves primarias
		ArrayList<Integer> clavesPrimariasPromocion = obtenerClavesPrimariasPromocion();

		// 2.Comprobamos que promoDiscount no coincida con ninguna clave primaria e
		// insertamos
		try {
			if (promoDescription == null || promoDiscount == 0) {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
			} else if (clavesPrimariasPromocion.contains(promoDiscount)) {
				JOptionPane.showMessageDialog(null, "Ya existe una promocion con ese descuento");
			} else {
				// 2.1 Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos
				// el metodo crear()
				ConexionManager conexionManager = new ConexionManager();
				Connection conexion = conexionManager.crear();

				// 2.2 Realizamos la insercion
				// 2.2.1.Creamos el PreparedStatement: Consulta, con valores desconocidos
				PreparedStatement preparedStatement = conexion
						.prepareStatement("INSERT INTO PROMOCION (DESCUENTOPROMO, DESCRIPCIONPROMO) VALUES (?, ?)");
				// 2.2.2.Decimos que en el valor desconocido 1 inserte el valor del String
				// promoDiscount
				preparedStatement.setInt(1, promoDiscount);
				// 2.2.3.Decimos que en el valor desconocido 2 inserte el valor del String
				// promoDescription
				preparedStatement.setString(2, promoDescription);
				// 2.2.4.Ejecutamos el preparedStatement
				preparedStatement.execute();
				// 2.2.5.Cerramos la conexion
				conexionManager.cerrar();
				// 2.2.6.Informamos
				JOptionPane.showMessageDialog(null, "Promocion creada correctamente");
				// 2.2.7. Actualizamos map
				cargarPromociones();
			}
		} catch (Exception e) {
			System.err.println("Excepcion no controlada al crear promocion");
			e.printStackTrace();
		}
	}

	/**
	 * Invoca cargarPromociones() Comprueba que no haya valores nulos. Ejecuta el
	 * update
	 * 
	 * @param oldPromoDiscount
	 * @param promoDescription
	 * @param promoDiscount
	 * @throws SQLException
	 */
	public void modificarPromocion(String promoDescription, int promoDiscount)
			throws SQLException {
		// 0.Cargamos promociones en el map y generamos la tabla
		cargarPromociones();
		generarTablaPromociones();

		// 1.Comprobamos que no haya valores nulos
		if (promoDescription == null || promoDiscount == 0) {
			JOptionPane.showMessageDialog(null, "Debe introducir valores validos");
		} else {
				// 2.1 Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos
				// el metodo crear()
				ConexionManager conexionManager = new ConexionManager();
				Connection conexion = conexionManager.crear();

				// 2.2 Realizamos la actualizacion
				// 2.2.1.Creamos el PreparedStatement: Update
				try {
					PreparedStatement preparedStatement = conexion.prepareStatement(
							"UPDATE PROMOCION (DESCUENTOPROMO, DESCRIPCIONPROMO) VALUES (?, ?)");// TODO: COMPROBAR
					// 2.2.2.Decimos que en el valor desconocido 1 inserte el valor del String
					// promoDiscount
					preparedStatement.setInt(1, promoDiscount);
					// 2.2.3.Decimos que en el valor desconocido 2 inserte el valor del String
					// promoDescription
					preparedStatement.setString(2, promoDescription);
					// 2.2.4.Ejecutamos el preparedStatement
					preparedStatement.execute();// TODO: COMPROBAR
					// 2.2.5.Cerramos la conexion
					conexionManager.cerrar();
				} catch (Exception e) {
					System.err.println("Excepcion no controlada");
					e.printStackTrace();
				}
				// 2.2.6.Informamos
				JOptionPane.showMessageDialog(null, "Promocion modificada correctamente");
				// 2.2.7. Actualizamos map
				cargarPromociones();
		}

	}

	/**
	 * Elimina la promocion cuyo descuento se introduce por parametro
	 * 
	 * @param promoDiscount
	 * @throws SQLException
	 */
	public void EliminarPromocion(int promoDiscount) throws SQLException {
		// 0.Cargamos promociones en el map
		cargarPromociones();

		// 1.Comprobamos que no haya valores nulos
		if (promoDiscount == 0) {
			JOptionPane.showMessageDialog(null, "Debe introducir valores validos");
		} else {
			if (mapPromocionesCreadas.containsKey(promoDiscount)) {
				// 2.1 Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos
				// el metodo crear()
				ConexionManager conexionManager = new ConexionManager();
				Connection conexion = conexionManager.crear();

				// 2.2 Realizamos la eliminacion
				// 2.2.1.Creamos el PreparedStatement: Update
				try {
					PreparedStatement preparedStatement = conexion
							.prepareStatement("DELETE FROM PROMOCION WHERE DESCUENTOPROMO=" + promoDiscount);// TODO:
																												// COMPROBAR
					// 2.2.2.Ejecutamos el preparedStatement
					preparedStatement.execute();
					// 2.2.3.Cerramos la conexion
					conexionManager.cerrar();
				} catch (Exception e) {
					System.err.println("Excepcion no controlada");
					e.printStackTrace();
				}
				// 2.2.4.Informamos
				JOptionPane.showMessageDialog(null, "Promocion eliminada correctamente");
				// 2.2.5. Actualizamos map
				cargarPromociones();
			} else {
				JOptionPane.showMessageDialog(null, "La promocion que busca ya no existe");
			}
		}
	}

	/**
	 * Prepara una tabla para refrescar la contenida en modificar promocion
	 * 
	 * @return tabla
	 * @throws SQLException
	 */
	public DefaultTableModel generarTablaPromociones() {
		// 1.Declaramos defaultTableModel,matriz tipo Object y string
		DefaultTableModel tablaPromocion = new DefaultTableModel();
		Object[][] resultado;
		String[] columnNames = { "Descuento promocion", "Descripcion promocion" };
		// 2.Abrimos la conexion
		try {
			ConexionManager conexionManager = new ConexionManager();
			Connection conexion = conexionManager.crear();
			// 3.Creamos el prepared statement que nos devolvera el numero de tuplas
			PreparedStatement count = conexion.prepareStatement("SELECT COUNT(*) AS NUM_PROMOCIONES FROM PROMOCION");
			ResultSet rs1 = count.executeQuery();
			rs1.next();//(*) Aqui saltaba la excepcion
			int numeroFilas = rs1.getInt("NUM_PROMOCIONES");
			rs1.close();
			// 4.Establecemos que la matriz sera del tamanio [numerofilas][2] donde 2 es el
			// numero de columnas.
			resultado = new Object[numeroFilas][2];

			// 5.Creamos el prepared statement que obtendra toda la info de la tabla
			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM PROMOCION");
			ResultSet rs2 = consulta.executeQuery();
			// 6.Obtenemos valores por tuplas
			int i = 0;
			while (rs2.next()) {
				String descripcionPromo = rs2.getString("DESCRIPCIONPROMO");
				int descuentoPromo = rs2.getInt("DESCUENTOPROMO");
				resultado[i][0] = descripcionPromo;
				resultado[i][1] = descuentoPromo;
				i++;
			}
			// 7.Cerramos la conexion
			conexionManager.cerrar();
			// 8.Creamos la tabla
			tablaPromocion.setDataVector(resultado, columnNames);
			return tablaPromocion;
		} catch (SQLException e) {
			System.err.println("Excepcion SQL no controlada");
			e.printStackTrace();
			return null;
		}
	}

}
