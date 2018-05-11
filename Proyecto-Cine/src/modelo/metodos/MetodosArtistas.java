package modelo.metodos;

import java.util.HashMap;

import modelo.POJOs.Artista;
import modelo.POJOs.Pais;
import modelo.POJOs.Pelicula;

public class MetodosArtistas {
	HashMap<String, Artista> Artistas=new HashMap<String,Artista>();
	public void CrearArtista(String nombreCompleto, Pais nacionalidad) {
		Artista artista=new Artista(nombreCompleto, nacionalidad);
		Artistas.put(nombreCompleto, artista);
	}
	public void EliminarArtista(String nombreCompleto) {
		Artistas.remove(nombreCompleto);
	}
	public void AnadirADirector(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaActor.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
	}
	public void AnadirAActor(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaDirector.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
	}
	public void masUnoCantidadPeliculas(String nombreCompleto) {
		if(Artistas.get(nombreCompleto).PeliculasParticipaDirector.containsKey(nombreCompleto) && Artistas.get(nombreCompleto).PeliculasParticipaActor.containsKey(nombreCompleto)) {
			System.out.println("No se suma + 1 en cantidad pelicula");
		}else if(Artistas.get(nombreCompleto).PeliculasParticipaDirector.containsKey(nombreCompleto)) {
			Artistas.get(nombreCompleto).setCantidadPeliculaParticipa(Artistas.get(nombreCompleto).getCantidadPeliculaParticipa()+1);
			System.out.println("Se suma + 1 en cantidad pelicula");
		}else if(Artistas.get(nombreCompleto).PeliculasParticipaActor.containsKey(nombreCompleto)) {
			Artistas.get(nombreCompleto).setCantidadPeliculaParticipa(Artistas.get(nombreCompleto).getCantidadPeliculaParticipa()+1);
			System.out.println("Se suma + 1 en cantidad pelicula");
		}
	}
}
