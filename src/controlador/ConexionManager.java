package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionManager {

	private static String URL_BBDD = "jdbc:mysql://localhost:3306/pruebaproyecto";

	private static String USUARIO = "root";

	private static String PASSWORD = "";

	private Connection conex;

	/**
	 * 
	 * @return Crea una conexion a la base de datos si no existe
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection crear() throws SQLException {
		try {
			if (conex == null) {
				Class.forName("com.mysql.jdbc.Driver");
				conex = DriverManager.getConnection(URL_BBDD, USUARIO, PASSWORD);
			}
			System.out.println("Conexion creada");
			return conex;
		} catch (ClassNotFoundException e) {
			System.out.println("Error al crear la conexion");
			throw new SQLException(e);
		}
	}

	/**
	 * Cierra la conexion con la base de datos
	 * 
	 * @throws SQLException
	 */
	public void cerrar() {
		if (conex != null) {
			try {
				conex.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar");
			} finally {
				conex = null;
				System.out.println("Conexion cerrada");
			}
		}
	}

	public Connection getConexion() throws SQLException {
		if (this.conex == null) {
			return crear();
		}
		return this.conex;
	}
}
