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
	public void A�adirADirector(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaActor.put(nombreCompleto, pelicula);
		
	}
	public void A�adirAActor(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaDirector.put(nombreCompleto, pelicula);
	}
}
