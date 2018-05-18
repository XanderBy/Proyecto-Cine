package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

import controlador.Conexion;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.POJOs.Compagnia;
import modelo.POJOs.Funcion;
import modelo.POJOs.Pelicula;
import modelo.POJOs.Promocion;
import modelo.POJOs.Sala;

public class MetodosFuncion extends Conexion {
	public static HashMap<LocalTime, Funcion> Funciones = new HashMap<LocalTime, Funcion>();

	// ---------------------------------------------------------
	public void crearFuncion(LocalTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion,
			Promocion promocionFuncion) {
		if (diaYHora == null || salaFuncion == null || peliculaFuncion == null || promocionFuncion == null) {
			JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
		} else {
			Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
			Funciones.put(diaYHora, funcion);
			JOptionPane.showMessageDialog(null, "Funcion Añadida");
		}
	}

	// ---------------------------------------------------------
	public void eliminarFuncion(LocalTime diaYHora) {
		Funciones.remove(diaYHora);
		JOptionPane.showMessageDialog(null, "Funcion Eliminada");
	}

	// ---------------------------------------------------------
	public void modificarFuncion(LocalTime diaYHoraAntiguo, LocalTime diaYHora, Sala salaFuncion,
			Pelicula peliculaFuncion, Promocion promocionFuncion) {
		if (diaYHoraAntiguo == null || diaYHora == null || salaFuncion == null || peliculaFuncion == null
				|| promocionFuncion == null) {
			JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
		} else {
			Funciones.remove(diaYHoraAntiguo);
			Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion, promocionFuncion);
			Funciones.put(diaYHora, funcion);
			JOptionPane.showMessageDialog(null, "Funcion modificada");
		}
	}

	// ---------------------------------------------------------
	public void anadirFuncionACine(String nombreCine, LocalTime diaYHora) {
		Compagnia.listaCines.get(nombreCine).funcionesSemana.put(diaYHora, Funciones.get(diaYHora));
	}

	// ---------------------------------------------------------
	public DefaultTableModel cogerFuncionBBDD() {
		System.out.println("prueba");
		DefaultTableModel tablemodel = new DefaultTableModel();
		int registros = 0;
		PreparedStatement pstm = null;
		String[] columNames = { "Dia y Hora" };
		// obtenemos la cantidad de registros existentes en la tabla y se almacena en la
		// variable "registros"
		// para formar la matriz de datos
		try {
			//TODO: No se si se llama asi la tabla
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
				data[i][0] = res.getString("nombreCine");
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

	// ---------------------------------------------------------
	public void eliminarFuncionBBDD() {

	}

	// ---------------------------------------------------------
	public void insertarFuncionBBDD() {

	}

	// ---------------------------------------------------------
	public void actualizarFuncionBBDD() {

	}

}
