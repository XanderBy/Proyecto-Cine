package modelo.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controlador.ConexionManager;
import modelo.POJOs.Artista;
import modelo.POJOs.CalificacionEdades;
import modelo.POJOs.GeneroPelicula;
import modelo.POJOs.IdiomaOriginal;
import modelo.POJOs.Pais;
import modelo.POJOs.Pelicula;

public class MetodosPelicula {
	
	public static HashMap<Integer,Pelicula> peliculas = new HashMap<Integer,Pelicula>();
	
	public MetodosPelicula() {
		
	}
	
	public void cargarPeliculas() throws SQLException {
		
		
		int agnoProduccion;
		String titulo;
		String tituloOriginal;
		GeneroPelicula genero;
		IdiomaOriginal idioma;
		boolean subtitulosEs;
		Pais paisOrigen;
		String sitioWeb;
		Duration duracion;
		CalificacionEdades calificacionEdades;
		LocalDate fechaEstrenoEs;
		String resumen;
		int idPelicula;
		Pelicula P;
		
		ConexionManager conManager = new ConexionManager();
		Connection conexion = conManager.crear();
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT agnoProduccion,titulo,tituloOriginal,genero,idioma,subtitulos,paisOrigen,sitioWeb,duracionPelicula,calificacionEdades,fechaEstrenoEspagna,resumen,idPelicula FROM pelicula");
			ResultSet resultado = consulta.executeQuery();
			
			while (resultado.next()) {
				agnoProduccion = resultado.getInt("agnoProduccion");
				titulo = resultado.getString("titulo");
				tituloOriginal = resultado.getString("tituloOriginal");
				genero = GeneroPelicula.valueOf(resultado.getString("genero").toUpperCase());
				idioma = IdiomaOriginal.valueOf(resultado.getString("idioma").toUpperCase());
				subtitulosEs = resultado.getBoolean("subtitulos");
				paisOrigen = Pais.valueOf(resultado.getString("paisOrigen").toUpperCase());
				sitioWeb = resultado.getString("sitioWeb");
				duracion = Duration.ofMinutes(resultado.getInt("duracionPelicula"));
				calificacionEdades = CalificacionEdades.valueOf(resultado.getString("calificacionEdades").toUpperCase());
				fechaEstrenoEs = resultado.getDate("fechaEstrenoEspagna").toLocalDate();
				resumen = resultado.getString("resumen");
				idPelicula = resultado.getInt("idPelicula");
				
				peliculas.put(idPelicula, new Pelicula(agnoProduccion,titulo,tituloOriginal,genero,idioma,subtitulosEs,paisOrigen,sitioWeb,duracion,calificacionEdades,fechaEstrenoEs,resumen,idPelicula));
				
			}
			
			conManager.cerrar();
			
		}catch(Exception e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}
	}
	
	public void cargarActorDirectorPelicula() throws SQLException {
		
		int idPelicula;
		String nombreCompleto;
		Pais nacionalidad;
		boolean actor;
		boolean director;
		
		ConexionManager conManager = new ConexionManager();
		Connection conexion = conManager.crear();
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT pelicula_idPelicula, artista_nombreCompleto,director,actor,nacionalidad FROM artistapelicula ap JOIN artista a ON ap.artista_nombreCompleto = a.nombreCompleto");
			ResultSet resultado = consulta.executeQuery();
			
			while(resultado.next()) {
				
				idPelicula = resultado.getInt("pelicula_idPelicula");
				nombreCompleto = resultado.getString("artista_nombreCompleto");
				director = resultado.getBoolean("director");
				actor = resultado.getBoolean("actor");
				nacionalidad = Pais.valueOf(resultado.getString("nacionalidad").toUpperCase());
				
				if (director) {
					peliculas.get(idPelicula).addDirector(new Artista (nombreCompleto,nacionalidad));
				}
				if (actor) {
					peliculas.get(idPelicula).addArtistaReparto(new Artista(nombreCompleto,nacionalidad));
				}
			}
			
			conManager.cerrar();
			
		} catch (Exception e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}
	}
	
	public boolean agnadirPelicula(int agnoProduccion,String tituloDistribucion, String tituloOriginal, String genero, String idioma, boolean subtitulosEs,String paisOrigen,String sitioWeb, Duration duracionPelicula, String calificacionEdades,LocalDate fechaEstrenoEs,String resumen,int idPelicula) throws SQLException {

		if (agnoProduccion == 0 || tituloDistribucion == null || tituloOriginal == null || genero == null || idioma == null || paisOrigen == null || sitioWeb == null || duracionPelicula == null || calificacionEdades == null || fechaEstrenoEs == null || resumen == null || idPelicula == 0) {
			
			JOptionPane.showMessageDialog(null, "Introduce valores validos");
			return false;
			
		}else{
			
			cargarPeliculas();
			
			try {
				
				if(peliculas.containsKey(idPelicula)) {
					
					ConexionManager conManager = new ConexionManager();
					Connection conexion = conManager.crear();
					PreparedStatement consulta = conexion.prepareStatement("INSERT INTO pelicula (agnoProduccion,titulo,tituloOriginal,genero,idioma,subtitulos,paisOrigen,sitioWeb,duracionPelicula,calificacionEdades,fechaEstrenoEspagna,resumen,idPelicula) VALUES (?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					
					consulta.setInt(1, agnoProduccion);
					consulta.setString(2, tituloDistribucion);
					consulta.setString(3, tituloOriginal);
					consulta.setString(4, genero);
					consulta.setString(5, idioma);
					consulta.setBoolean(6, subtitulosEs);
					consulta.setString(7, paisOrigen);
					consulta.setString(8, sitioWeb);
					consulta.setLong(9, duracionPelicula.toMinutes());
					consulta.setString(10, calificacionEdades);
					consulta.setString(11, fechaEstrenoEs.toString());
					consulta.setString(12, resumen);
					consulta.setInt(13, idPelicula);
					
					consulta.execute();
					conManager.cerrar();
					
					JOptionPane.showMessageDialog(null, "Pelicula creada correctamente");
					cargarPeliculas();
					
				}else{
					
					JOptionPane.showMessageDialog(null, "Ya hay una pelicula con el mismo IdPelicula");
					return false;
					
				}
				
			}catch(Exception e) {
				System.out.println("Excepcion no controlada");
				e.printStackTrace();
				return false;
			}
			return true;
			
		}
		
	}
	
	public boolean modificarPelicula(int idPelicula,int agnoProduccion,String tituloDistribucion, String tituloOriginal, String genero, String idioma, boolean subtitulosEs,String paisOrigen,String sitioWeb, Duration duracionPelicula, String calificacionEdades,LocalDate fechaEstrenoEs,String resumen) throws SQLException {
		
		if (agnoProduccion == 0 || tituloDistribucion == null || tituloOriginal == null || genero == null || idioma == null || paisOrigen == null || sitioWeb == null || duracionPelicula == null || calificacionEdades == null || fechaEstrenoEs == null || resumen == null || idPelicula == 0) {
			
			JOptionPane.showMessageDialog(null, "Introduce valores validos");
			return false;
			
		}else{
			
			cargarPeliculas();
			
			if (peliculas.containsKey(idPelicula)) {
				
				ConexionManager conManager = new ConexionManager();
				Connection conexion = conManager.crear();
				
				try {
					
					PreparedStatement consulta = conexion.prepareStatement("UPDATE pelicula SET agnoProduccion = ?,titulo = ?,tituloOriginal = ?,genero = ?,idioma = ?,subtitulos = ?,paisOrigen = ?,sitioWeb = ?,duracionPelicula = ?,calificacionEdades = ?,fechaEstrenoEspagna = ?,resumen = ? WHERE idPelicula = " + idPelicula);
					
					consulta.setInt(1, agnoProduccion);
					consulta.setString(2, tituloDistribucion);
					consulta.setString(3, tituloOriginal);
					consulta.setString(4, genero);
					consulta.setString(5, idioma);
					consulta.setBoolean(6, subtitulosEs);
					consulta.setString(7, paisOrigen);
					consulta.setString(8, sitioWeb);
					consulta.setLong(9, duracionPelicula.toMinutes());
					consulta.setString(10, calificacionEdades);
					consulta.setString(11, fechaEstrenoEs.toString());
					consulta.setString(12, resumen);
					
					consulta.execute();
					conManager.cerrar();
					
					JOptionPane.showMessageDialog(null, "Pelicula modificada correctamente");
					cargarPeliculas();
					
					return true;
					
				}catch(Exception e) {
					System.out.println("Excepcion no controlada");
					e.printStackTrace();
					return false;
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "Pelicula no encontrada");
				return false;
			}
		}
	}
	
	public boolean agnadirActor(int idPelicula,String nombreCompleto) throws SQLException {
		
		if (idPelicula == 0 || nombreCompleto == null) {
			
			JOptionPane.showMessageDialog(null, "Introduce valores validos");
			return false;
			
		}else{
			
			cargarPeliculas();
			cargarActorDirectorPelicula();
			
			MetodosArtistas a = new MetodosArtistas();
			a.cogerArtistaBBDD();
			
			ConexionManager conManager = new ConexionManager();
			Connection conexion = conManager.crear();
			
			try {
				
				if (modelo.metodos.MetodosArtistas.Artistas.containsKey(nombreCompleto)) {
					
					PreparedStatement consulta = conexion.prepareStatement("INSERT INTO artistapelicula	(artista_nombreCompleto,pelicula_idPelicula,director,actor) VALUES (?, ?, ?, ?)");
					consulta.setString(1, nombreCompleto);
					consulta.setInt(2, idPelicula);
					consulta.setBoolean(3, false);
					consulta.setBoolean(4, true);
					consulta.execute();
					conManager.cerrar();
					
					a.masUnoCantidadPeliculas(nombreCompleto);
					
					JOptionPane.showMessageDialog(null, "Actor introducido en reparto");
					return true;
					
				}else{
					
					JOptionPane.showMessageDialog(null, "Debe crear previamente el Artista");
					return false;
					
				}
				
			}catch(Exception e) {
				System.out.println("Excepcion no controlada");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean agnadirDirector(int idPelicula,String nombreCompleto) throws SQLException {
		
		if (idPelicula == 0 || nombreCompleto == null) {
			
			JOptionPane.showMessageDialog(null, "Introduce valores validos");
			return false;
			
		}else{
			
			cargarPeliculas();
			cargarActorDirectorPelicula();
			
			MetodosArtistas a = new MetodosArtistas();
			a.cogerArtistaBBDD();
			
			ConexionManager conManager = new ConexionManager();
			Connection conexion = conManager.crear();
			
			try {
				
				if (modelo.metodos.MetodosArtistas.Artistas.containsKey(nombreCompleto)) {
					
					PreparedStatement consulta = conexion.prepareStatement("INSERT INTO artistapelicula	(artista_nombreCompleto,pelicula_idPelicula,director,actor) VALUES (?, ?, ?, ?)");
					consulta.setString(1, nombreCompleto);
					consulta.setInt(2, idPelicula);
					consulta.setBoolean(3, true);
					consulta.setBoolean(4, false);
					consulta.execute();
					conManager.cerrar();
					
					a.masUnoCantidadPeliculas(nombreCompleto);
					
					JOptionPane.showMessageDialog(null, "Director introducido");
					return true;
					
				}else{
					
					JOptionPane.showMessageDialog(null, "Debe crear previamente el Artista");
					return false;
					
				}
				
			}catch(Exception e) {
				System.out.println("Excepcion no controlada");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean eliminarPelicula(String nombre) throws SQLException {
		
		cargarPeliculas();
		boolean resultado=false;
		
		ConexionManager conManager = new ConexionManager();
		Connection conexion = conManager.crear();
		
		try {
			
			Iterator it = peliculas.keySet().iterator();
			while (it.hasNext()) {
				
				int key = (int) it.next();
				
				if (peliculas.get(key).getTituloDistribucion().equalsIgnoreCase(nombre)) {
					PreparedStatement consulta = conexion.prepareStatement("DELETE FROM pelicula WHERE idPelicula = " + key);
					consulta.execute();
					conManager.cerrar();
					JOptionPane.showMessageDialog(null, "Pelicula eliminada correctamente");
					cargarPeliculas();
					resultado=true;
				}
			}
			
			if (resultado != true) {
				JOptionPane.showMessageDialog(null, "Pelicula no encontrada", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
			}
		}catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}
	
	public DefaultTableModel generarTablaPeliculas() {
		
		DefaultTableModel tablaPeliculas = new DefaultTableModel();
		Object [][] resultado;
		String [] nombreColumnas = { "titulo","idPelicula" };
		
		try {
			
			ConexionManager conManager = new ConexionManager();
			Connection conexion = conManager.crear();
		
			PreparedStatement consulta = conexion.prepareStatement("SELECT COUNT(*) numPeliculas FROM pelicula");
			ResultSet resultado1 = consulta.executeQuery();
			resultado1.next();
			int numFilas = resultado1.getInt("numPeliculas");
			resultado1.close();
			
			resultado = new Object[numFilas][2];
			
			PreparedStatement consulta2 = conexion.prepareStatement("SELECT titulo,idPelicula FROM pelicula");
			ResultSet resultado2 = consulta2.executeQuery();
			
			int i = 0;
			
			while (resultado2.next()) {
				
				String titulo = resultado2.getString("titulo");
				int idPelicula = resultado2.getInt("idPelicula");
				
				resultado[i][0] = titulo;
				resultado[i][1] = idPelicula;
				i++;
			}
			conManager.cerrar();
			tablaPeliculas.setDataVector(resultado, nombreColumnas);
			return tablaPeliculas;
		}catch(SQLException e) {
			System.err.println("Excepcion SQL no controlada");
			e.printStackTrace();
			return null;
		}
	}
}
