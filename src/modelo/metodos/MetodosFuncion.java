package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;

import controlador.Conexion;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;
import modelo.POJOs.Funcion;
import modelo.POJOs.Pelicula;
import modelo.POJOs.Promocion;
import modelo.POJOs.Sala;

public class MetodosFuncion extends Conexion {
	public static HashMap<LocalTime, Funcion> Funciones = new HashMap<LocalTime, Funcion>();
	/*
	 * Falta
	 * 
	 * falta coger las funcionesSemana linea 81 falta implementar coger funciones
	 * 
	 * 
	 */

	// ---------------------------------------------------------
	public void crearFuncion(LocalTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion,
			Promocion promocionFuncion) {
		if (diaYHora == null || salaFuncion == null || peliculaFuncion == null || promocionFuncion == null) {
			JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
		} else {
			// Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion,
			// promocionFuncion);
			// Funciones.put(diaYHora, funcion);
			crearFuncionBBDD(diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
			eliminarFuncionesArray();
			cogerTodasLasFuncionesBBDD();

			JOptionPane.showMessageDialog(null, "Funcion Aniadida");
		}
	}

	// ---------------------------------------------------------
	public void eliminarFuncion(LocalTime diaYHora) {
		// Funciones.remove(diaYHora);

		eliminarFuncionBBDD(diaYHora);
		eliminarFuncionesArray();// TODO:Aqui a lo mejor de error
		cogerTodasLasFuncionesBBDD();
		JOptionPane.showMessageDialog(null, "Funcion Eliminada");
	}

	// ---------------------------------------------------------
	public void modificarFuncion(LocalTime diaYHoraAntiguo, LocalTime diaYHora, Sala salaFuncion,
			Pelicula peliculaFuncion, Promocion promocionFuncion) {
		if (diaYHoraAntiguo == null || diaYHora == null || salaFuncion == null || peliculaFuncion == null
				|| promocionFuncion == null) {
			JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
		} else {
			// Funciones.remove(diaYHoraAntiguo);
			// Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion,
			// promocionFuncion);
			// Funciones.put(diaYHora, funcion);
			actualizarFuncionBBDD(diaYHoraAntiguo, diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
			eliminarFuncionesArray();
			cogerTodasLasFuncionesBBDD();
			JOptionPane.showMessageDialog(null, "Funcion modificada");
		}
	}

	// ---------------------------------------------------------
	public void anadirFuncionACine(String nombreCine, LocalTime diaYHora) {
		Compagnia.listaCines.get(nombreCine).funcionesSemana.put(diaYHora, Funciones.get(diaYHora));
		// TODO: Aqui falta
		eliminarFuncionesSemanaArray();
		// TODO: Tambien falta coger las funcionesSemana
	}

	// ---------------------------------------------------------
	public void eliminarFuncionesArray() {
		Iterator it = Compagnia.listaCines.keySet().iterator();
		while (it.hasNext()) {
			String clave = (String) it.next();
			Funciones.remove(clave);
		}
	}

	// TODO: Creo que esta implementado
	// ---------------------------------------------------------
	public void eliminarFuncionesSemanaArray() {
		Iterator it = Funciones.keySet().iterator();
		Iterator ito = Compagnia.listaCines.keySet().iterator();
		while (ito.hasNext()) {
			String clave = (String) ito.next();

			do {
				while (it.hasNext()) {
					String clave1 = (String) it.next();
					Compagnia.listaCines.get(clave).funcionesSemana.remove(clave1);
				}
			} while (Compagnia.listaCines.get(clave).funcionesSemana.size() != 0);
			System.out.println("El tamaño del array de funciones semana es "
					+ Compagnia.listaCines.get(clave).funcionesSemana.size());
		}

	}

	// ---------------------------------------------------------
	public void eliminarFuncionBBDD(LocalTime diaYHora) {
		// se arma la consulta
		String q = " DELETE FROM funcion WHERE  diaYHora='" + diaYHora + "' ";
		// se ejecuta la consulta
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// ---------------------------------------------------------
	public void crearFuncionBBDD(LocalTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion,
			Promocion promocionFuncion) {
		// se arma la consulta
		String q = " INSERT INTO funcion (diaYHora, salaFuncion, peliculaFuncion, promocionFuncion)" + "VALUES ("
				+ diaYHora + "," + salaFuncion + "," + peliculaFuncion + "," + promocionFuncion + ")";
		// se ejecuta la consulta
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// ---------------------------------------------------------
	public void actualizarFuncionBBDD(LocalTime diaYHoraAntiguo, LocalTime diaYHora, Sala salaFuncion,
			Pelicula peliculaFuncion, Promocion promocionFuncion) {
		// se arma la consulta
		String q = " UPDATE funcion " + "SET diaYHora = '" + diaYHora + "', salaFuncion = '" + salaFuncion
				+ "', peliculaFuncion = '" + peliculaFuncion + "', promocionFuncion = '" + promocionFuncion + "'"
				+ "WHERE diaYHora= '" + diaYHoraAntiguo + " '";
		// se ejecuta la consulta
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// ---------------------------------------------------------
	public void cogerTodasLasFuncionesBBDD() {
		PreparedStatement pstm = null;
		try {
			pstm = this.getConexion()
					.prepareStatement("SELECT diaYHora, salaFuncion, peliculaFuncion, promocionFuncion FROM funcion");
			ResultSet res = pstm.executeQuery();
			int i = 0;
			while (res.next()) {
				String a= res.getString("salaFuncion");
				Object b= a;
				//Funcion funcion=new Funcion((LocalTime) res.getString("diaYHora"), res.getString("salaFuncion"), res.getString("peliculaFuncion"), res.getString("promocionFuncion"))
				//Funciones.put(res.getString("diaYHora"), funcion);
				i++;
			}
			res.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// ---------------------------------------------------------
	public void cogerTodasLasFuncionesSemanaBBDD() {

	}

	// ---------------------------------------------------------
	public DefaultTableModel cogerFuncionBBDDNombre() {
		System.out.println("prueba");
		DefaultTableModel tablemodel = new DefaultTableModel();
		int registros = 0;
		PreparedStatement pstm = null;
		String[] columNames = { "Dia y Hora" };
		// obtenemos la cantidad de registros existentes en la tabla y se almacena en la
		// variable "registros"
		// para formar la matriz de datos
		try {
			// TODO: No se si se llama asi la tabla
			pstm = getConexion().prepareStatement("SELECT count(*) as total FROM funcion");
			ResultSet res = pstm.executeQuery();
			res.next();
			registros = res.getInt("total");
			res.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		// se crea una matriz con tantas filas y columnas que necesite
		Object[][] data = new String[registros][1];
		try {
			// realizamos la consulta sql y llenamos los datos en la matriz "Object[][]
			// data"
			pstm = this.getConexion().prepareStatement("SELECT diaYHora FROM Funcion");
			ResultSet res = pstm.executeQuery();
			int i = 0;
			while (res.next()) {
				data[i][0] = res.getString("diaYHora");
				i++;
			}
			res.close();
			// se anade la matriz de datos en el DefaultTableModel
			tablemodel.setDataVector(data, columNames);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return tablemodel;

	}

}
