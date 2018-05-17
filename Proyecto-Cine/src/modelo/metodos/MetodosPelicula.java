package modelo.metodos;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;
import modelo.POJOs.Artista;
import modelo.POJOs.CalificacionEdades;
import modelo.POJOs.GeneroPelicula;
import modelo.POJOs.IdiomaOriginal;
import modelo.POJOs.Pais;
import modelo.POJOs.Pelicula;

public class MetodosPelicula {
	
	public static HashMap<Integer,Pelicula> peliculas = new HashMap<Integer,Pelicula>();
	
	public boolean agnadirPelicula(Pelicula p, int idPelicula) {

		try {
			peliculas.put(idPelicula, p);
			return true;
			
		}catch(Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificarPelicula(int idPelicula,int agnoProduccion,String tituloDistribucion, String tituloOriginal, GeneroPelicula genero, IdiomaOriginal idioma, boolean subtitulosEs,Pais paisOrigen,String sitioWeb, Duration duracionPelicula, CalificacionEdades calificacionEdades,LocalDate fechaEstrenoEs,String resumen) {
		
		try {
			Pelicula aux = peliculas.get(idPelicula);
			aux.setAgnoProduccion(agnoProduccion);
			aux.setTituloDistribucion(tituloDistribucion);
			aux.setTituloOriginal(tituloOriginal);
			aux.setGenero(genero);
			aux.setIdioma(idioma);
			aux.setSubtitulosEs(subtitulosEs);
			aux.setPaisOrigen(paisOrigen);
			aux.setSitioWeb(sitioWeb);
			aux.setDuracionPelicula(duracionPelicula);
			aux.setCalificacionEdades(calificacionEdades);
			aux.setFechaEstrenoEs(fechaEstrenoEs);
			aux.setResumen(resumen);
			peliculas.put(idPelicula, aux);
			return true;
		}catch(Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean agnadirActor(int idPelicula,String nombreCompleto, Pais nacionalidad,String roll) {
		
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
	}
	
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
