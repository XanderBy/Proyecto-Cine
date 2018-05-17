package modelo.metodos;

import java.util.HashMap;

import controlador.Conexion;
import javax.swing.JOptionPane;
import modelo.POJOs.Artista;
import modelo.POJOs.Pais;
import modelo.POJOs.Pelicula;

public class MetodosArtistas extends Conexion {
	public static HashMap<String, Artista> Artistas = new HashMap<String, Artista>();

	// Creas Artista
	public void CrearArtista(String nombreCompleto, Pais nacionalidad) {
            if(nombreCompleto==null || nacionalidad==null){
                JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
            }else{
		Artista artista = new Artista(nombreCompleto, nacionalidad);
		Artistas.put(nombreCompleto, artista);
                JOptionPane.showMessageDialog(null, "Artista creador con exito");
            }
	}

	// Anade el artista al Array director
	public void AnadirADirector(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaActor.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
                JOptionPane.showMessageDialog(null, "Artista Añadido ha director");
	}

	// Anade el artista al Array actor
	public void AnadirAActor(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaDirector.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
                JOptionPane.showMessageDialog(null, "Artista Añadido ha Actor");
	}

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

	public void eliminarArtistaBBDD() {

	}

	public void insertarArtistaBBDD() {

	}

	public void actualizarArtistaBBDD() {

	}

	public void cogerArtistaBBDD() {

	}
}
