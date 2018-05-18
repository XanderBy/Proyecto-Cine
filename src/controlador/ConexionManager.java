package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionManager {

	private static String URL_BBDD = "jdbc:mysql://localhost:3306/bbddCine";

	private static String USUARIO = "root";

	private static String PASSWORD = "";

	private Connection conex;

	/**
	 * 
	 * @return Crea una conexión a la base de datos si no existe
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Connection crear() throws ClassNotFoundException, SQLException {
		if (conex == null) {
			Class.forName("com.mysql.jdbc.Driver");
			conex = DriverManager.getConnection(URL_BBDD, USUARIO, PASSWORD);
		}
		return conex;
	}

	/**
	 * Cierra la conexión con la base de datos
	 * 
	 * @throws SQLException
	 */
	public void cerrar() {
		if (conex != null) {
			try {
				conex.close();
			} catch (SQLException e) {
				// No se hace nada si se produce un error
			} finally {
				conex = null;
			}
		}
	}


	public Connection getConexion() {
		return this.conex;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConexionManager conexion = new ConexionManager();
		Connection conn = conexion.crear();
		if(conn!= null){
			System.out.println("Hay conexion!");
			conexion.cerrar();
		}else {
			System.out.println("algo ha ido mal");
		}
		
	}
	
	
}
