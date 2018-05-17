package modelo.POJOs;

import java.util.HashMap;

public class Artista {
	//TODO: Atributos
	private String nombreCompleto;
	private Pais nacionalidad;
	private int cantidadPeliculaParticipa;
	public HashMap <String, Pelicula> PeliculasParticipaDirector=new HashMap <String,Pelicula>();
	public HashMap <String, Pelicula> PeliculasParticipaActor=new HashMap <String,Pelicula>();
	
	
	//TODO: Contructores
	public Artista(String nombreCompleto, Pais nacionalidad) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.nacionalidad = nacionalidad;
	}
	
	//TODO: Metodos Get/Set
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
	public int getCantidadPeliculaParticipa() {
		return cantidadPeliculaParticipa;
	}
	public void setCantidadPeliculaParticipa(int cantidadPeliculaParticipa) {
		this.cantidadPeliculaParticipa = cantidadPeliculaParticipa;
	}

}
