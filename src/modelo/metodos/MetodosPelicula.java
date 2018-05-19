package modelo.metodos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
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
	
	public boolean agnadirPelicula(int agnoProduccion,String tituloDistribucion, String tituloOriginal, String genero, String idioma, boolean subtitulosEs,String paisOrigen,String sitioWeb, Duration duracionPelicula, String calificacionEdades,LocalDate fechaEstrenoEs,String resumen,int idPelicula) throws SQLException {

		cargarPeliculas();
		
		try {
			
			if(modelo.metodos.MetodosGenerales.encuentraKeyIntHashMap(peliculas, idPelicula)) {
				
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
	
	public boolean modificarPelicula(int idPelicula,int agnoProduccion,String tituloDistribucion, String tituloOriginal, String genero, String idioma, boolean subtitulosEs,String paisOrigen,String sitioWeb, Duration duracionPelicula, String calificacionEdades,LocalDate fechaEstrenoEs,String resumen) throws SQLException {
		
		cargarPeliculas();
		
		if (modelo.metodos.MetodosGenerales.encuentraKeyIntHashMap(peliculas, idPelicula)) {
			
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
	
	/*public boolean agnadirActor(int idPelicula,String nombreCompleto, Pais nacionalidad,String roll) throws SQLException {
		
		//cargarPeliculas();
		
		//MetodosArtistas a = new MetodosArtistas();
		//a.cogerArtistaBBDD();
		
		
		//if (modelo.metodos.MetodosGenerales.encuentraKeyStringHashMap(modelo.metodos.MetodosArtistas.Artistas, nombreCompleto)) {
			
		//}
		
		try {
			
			peliculas.get(idPelicula).addArtistaReparto(new Artista(nombreCompleto,nacionalidad),roll);
			return true;
			
		}catch(Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean agnadirDirector(int idPelicula,String nombreCompleto,Pais nacionalidad) {
		try {
			
			peliculas.get(idPelicula).addDirector(new Artista(nombreCompleto,nacionalidad));
			return true;
			
		}catch(Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return false;
		}
	}*/
	
	public boolean eliminarPelicula(String nombre) {
		
		boolean resultado=false;
		
		try {
			
			Iterator it = peliculas.keySet().iterator();
			while (it.hasNext()) {
				
				int key = (int) it.next();
				
				if (peliculas.get(key).getTituloDistribucion().equalsIgnoreCase(nombre)) {
					peliculas.remove(key);
					resultado=true;
				}else{
					JOptionPane.showMessageDialog(null, "Pelicula no encontrada", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
		}catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return resultado;
		}

		return resultado;
	}

}
