package modelo.POJOs;

import java.util.HashMap;

public class Artista {
	private String nombreCompleto;
	private Pais nacionalidad;
	private static int cantidadPeliculaParticipa;
	public HashMap <String, Pelicula> PeliculasParticipaDirector=new HashMap <String,Pelicula>();
	public HashMap <String, Pelicula> PeliculasParticipaActor=new HashMap <String,Pelicula>();
	
	
	
	public Artista(String nombreCompleto, Pais nacionalidad) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.nacionalidad = nacionalidad;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Pais getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public static int getCantidadPeliculaParticipa() {
		return cantidadPeliculaParticipa;
	}
	public static void setCantidadPeliculaParticipa(int cantidadPeliculaParticipa) {
		Artista.cantidadPeliculaParticipa = cantidadPeliculaParticipa;
	}

}
