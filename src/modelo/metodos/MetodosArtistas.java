package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import controlador.ConexionManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.POJOs.Artista;
import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;
import modelo.POJOs.Pais;
import modelo.POJOs.Pelicula;

public class MetodosArtistas extends ConexionManager {
	public static HashMap<String, Artista> Artistas = new HashMap<String, Artista>();

	// Creas Artista
	// ------------------------------------------------------
	public void CrearArtista(String nombreCompleto, Pais nacionalidad) {
		if (nombreCompleto == null || nacionalidad == null) {
			JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
		} else {
			Artista artista = new Artista(nombreCompleto, nacionalidad);
			Artistas.put(nombreCompleto, artista);
			JOptionPane.showMessageDialog(null, "Artista creador con exito");
		}
	}

	// ------------------------------------------------------
	public void eliminarArtista(String nombreCompleto) {
		if(nombreCompleto.length()==0 || nombreCompleto.equals("Nombre Completo")) {
			JOptionPane.showMessageDialog(null, "No has elegido ningun artista");
		}else {
		eliminarArtistaBBDD(nombreCompleto);
		JOptionPane.showMessageDialog(null, "Artista eliminado con exito");
		}
	}
	// ------------------------------------------------------
	public void modificarArtista(String nombreCompletoAntiguo, String nombreCompleto, String nacionalidad, int cantidadPeliculas) {
		actualizarArtistaBBDD(nombreCompletoAntiguo, nombreCompleto, nacionalidad, cantidadPeliculas);
		JOptionPane.showMessageDialog(null, "Artista modificado con exito");
		
	}
	// ------------------------------------------------------
	// Anade el artista al Array director
	public void AnadirADirector(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaActor.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
		JOptionPane.showMessageDialog(null, "Artista Aniadido ha director");
	}

	// ------------------------------------------------------
	// Anade el artista al Array actor
	public void AnadirAActor(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaDirector.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
		JOptionPane.showMessageDialog(null, "Artista Añadido ha Actor");
	}

	// ------------------------------------------------------
	// Este metodo anade +1 a la cantidad de peliculas del Artista
	public void masUnoCantidadPeliculas(String nombreCompleto) {
		if (Artistas.get(nombreCompleto).PeliculasParticipaDirector.containsKey(nombreCompleto)
				&& Artistas.get(nombreCompleto).PeliculasParticipaActor.containsKey(nombreCompleto)) {
			System.out.println("No se suma + 1 en cantidad pelicula");

		} else if (Artistas.get(nombreCompleto).PeliculasParticipaDirector.containsKey(nombreCompleto)) {
			Artistas.get(nombreCompleto)
					.setCantidadPeliculaParticipa(Artistas.get(nombreCompleto).getCantidadPeliculaParticipa() + 1);
			System.out.println("Se suma + 1 en cantidad pelicula");

		} else if (Artistas.get(nombreCompleto).PeliculasParticipaActor.containsKey(nombreCompleto)) {
			Artistas.get(nombreCompleto)
					.setCantidadPeliculaParticipa(Artistas.get(nombreCompleto).getCantidadPeliculaParticipa() + 1);
			System.out.println("Se suma + 1 en cantidad pelicula");

		}
	}

	// ------------------------------------------------------
	public void eliminarArtistaBBDD(String nombreCompleto) {
		String q = " DELETE FROM artista where nombreCompleto='" + nombreCompleto + "'";
		// se ejecuta la consulta
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		devolverArtistasArray();
	}
	// ------------------------------------------------------
		public void insertarArtistaBBDD(String nombre, String nacionalidad, int cantidadPeliculas) {
			// se arma la consulta
			String q = " INSERT INTO artista (nombreCompleto, nacionalidad, cantidadPeliculas)" + "VALUES ('" + nombre + "','"
					+ nacionalidad + "'," + cantidadPeliculas + ")";
			// se ejecuta la consulta
			try {
				PreparedStatement pstm = this.getConexion().prepareStatement(q);
				pstm.execute();
				pstm.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		}
	// ------------------------------------------------------
	public void insertarArtistaActorBBDD(String nombre, int pelicula_idPelicula) {
		// se arma la consulta
		String q = " INSERT INTO artistapelicula (artista_nombreCompleto, pelicula_idPelicula, actor)" + "VALUES ('" + nombre + "','"
				+ pelicula_idPelicula + "'," + 1 + ")";
		// se ejecuta la consulta
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}
	// ------------------------------------------------------
		public void insertarArtistaDirectorBBDD(String nombre, int pelicula_idPelicula) {
			// se arma la consulta
			String q = " INSERT INTO artistapelicula (artista_nombreCompleto, pelicula_idPelicula, director)" + "VALUES ('" + nombre + "','"
					+ pelicula_idPelicula + "'," + 1 + ")";
			// se ejecuta la consulta
			try {
				PreparedStatement pstm = this.getConexion().prepareStatement(q);
				pstm.execute();
				pstm.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		}
	// ------------------------------------------------------
	public void eliminarArtistasArray() {
		Iterator it = Artistas.keySet().iterator();
		while (it.hasNext()) {
			String clave = (String) it.next();
			Artistas.remove(clave);
		}
	}
	

	// ------------------------------------------------------
	public void devolverArtistasArray() {
		// se arma la consulta
		String q = "SELECT nombreCompleto, nacionalidad, cantidadPeliculas FROM artista ";
		// se ejecuta la consulta
		try {
			Pais enumerador = null;
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			ResultSet res = pstm.executeQuery();
			eliminarArtistasArray();
			int i = 0;
			while (res.next()) {
				enumerador = enumerador.valueOf(res.getString("nacionalidad"));

				Artista artista = new Artista(res.getString("nombreCompleto"), enumerador);
				artista.setCantidadPeliculaParticipa(Integer.parseInt(res.getString("cantidadPeliculas")));
				Artistas.put(res.getString("nombreCompleto"), artista);
				i++;
			}
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// ------------------------------------------------------
	public void actualizarArtistaBBDD(String nombreCompeltoAntiguo, String nombreCompleto, String nacionalidad, int numeroPelicula) {
		String q = " UPDATE artista " + "SET nombreCompleto = '" + nombreCompleto + "', nacionalidad='" + nacionalidad
				+ "', cantidadPeliculas= "+ numeroPelicula + " WHERE nombreCompleto= '" + nombreCompeltoAntiguo + " '";

		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// ------------------------------------------------------
	public DefaultTableModel cogerArtistaBBDDTodo() {
		System.out.println("pruebaaaaaaaa");
		DefaultTableModel tablemodel = new DefaultTableModel();
		int registros = 0;
		PreparedStatement pstm = null;
		String[] columNames = { "nombre Completo", "nacionalidad", "cantidad Peliculas" };
		// obtenemos la cantidad de registros existentes en la tabla y se almacena en la
		// variable "registros"
		// para formar la matriz de datos
		try {
			System.out.println("pruebass");
			pstm = getConexion().prepareStatement("SELECT count(*) as total FROM artista");
			ResultSet res = pstm.executeQuery();
			res.next();
			registros = res.getInt("total");
			res.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		// se crea una matriz con tantas filas y columnas que necesite
		Object[][] data = new String[registros][3];
		try {
			// realizamos la consulta sql y llenamos los datos en la matriz "Object[][]
			// data"
			pstm = this.getConexion()
					.prepareStatement("SELECT nombreCompleto, nacionalidad, cantidadPeliculas FROM artista");
			ResultSet res = pstm.executeQuery();
			int i = 0;
			while (res.next()) {
				data[i][0] = res.getString("nombreCompleto");
				data[i][1] = res.getString("nacionalidad");
				data[i][2] = res.getString("cantidadPeliculas");
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
