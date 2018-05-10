package modelo.POJOs;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;

public class Pelicula {
	
	private int agnoProduccion;
	private String tituloDistribucion;
	private String tituloOriginal;
	private GeneroPelicula genero;
	private IdiomaOriginal idioma;
	private boolean subtitulosEs;
	private Pais paisOrigen;
	private String sitioWeb;
	private Duration duracionPelicula;
	private CalificacionEdades calificacionEdades;
	private LocalDate fechaEstrenoEs;
	private String resumen;
	private int idPelicula;
	//private HashMap<String,Persona> directoresPelicula;
	private HashMap<String,String> repartoPelicula;
	private HashMap<String,Opinion> opiniones;

	public Pelicula(int agnoProduccion,String tituloDistribucion, String tituloOriginal, GeneroPelicula genero, IdiomaOriginal idioma, boolean subtitulosEs,Pais paisOrigen,String sitioWeb, Duration duracionPelicula, CalificacionEdades calificacionEdades,LocalDate fechaEstrenoEs,String resumen, int idPelicula) {
		
		this.agnoProduccion = agnoProduccion;
		this.tituloDistribucion = tituloDistribucion;
		this.tituloOriginal = tituloOriginal;
		this.genero = genero;
		this.idioma = idioma;
		this.subtitulosEs = subtitulosEs;
		this.paisOrigen = paisOrigen;
		this.sitioWeb = sitioWeb;
		this.duracionPelicula = duracionPelicula;
		this.calificacionEdades = calificacionEdades;
		this.fechaEstrenoEs = fechaEstrenoEs;
		this.resumen = resumen;
		this.idPelicula = idPelicula;
		//directoresPelicula = new HashMap<String,Persona>();
		repartoPelicula = new HashMap<String,String>();
		opiniones = new HashMap<String,Opinion>();
		
	}

	public int getAgnoProduccion() {
		return agnoProduccion;
	}

	public void setAgnoProduccion(int agnoProduccion) {
		this.agnoProduccion = agnoProduccion;
	}

	public String getTituloDistribucion() {
		return tituloDistribucion;
	}

	public void setTituloDistribucion(String tituloDistribucion) {
		this.tituloDistribucion = tituloDistribucion;
	}

	public String getTituloOriginal() {
		return tituloOriginal;
	}

	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}

	public GeneroPelicula getGenero() {
		return genero;
	}

	public void setGenero(GeneroPelicula genero) {
		this.genero = genero;
	}

	public IdiomaOriginal getIdioma() {
		return idioma;
	}

	public void setIdioma(IdiomaOriginal idioma) {
		this.idioma = idioma;
	}

	public boolean isSubtitulosEs() {
		return subtitulosEs;
	}

	public void setSubtitulosEs(boolean subtitulosEs) {
		this.subtitulosEs = subtitulosEs;
	}

	public Pais getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(Pais paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public Duration getDuracionPelicula() {
		return duracionPelicula;
	}

	public void setDuracionPelicula(Duration duracionPelicula) {
		this.duracionPelicula = duracionPelicula;
	}

	public CalificacionEdades getCalificacionEdades() {
		return calificacionEdades;
	}

	public void setCalificacionEdades(CalificacionEdades calificacionEdades) {
		this.calificacionEdades = calificacionEdades;
	}

	public LocalDate getFechaEstrenoEs() {
		return fechaEstrenoEs;
	}

	public void setFechaEstrenoEs(LocalDate fechaEstrenoEs) {
		this.fechaEstrenoEs = fechaEstrenoEs;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public HashMap<String, String> getRepartoPelicula() {
		return repartoPelicula;
	}

	public void setRepartoPelicula(HashMap<String, String> repartoPelicula) {
		this.repartoPelicula = repartoPelicula;
	}

	public HashMap<String, Opinion> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(HashMap<String, Opinion> opiniones) {
		this.opiniones = opiniones;
	}
	
	public String toString() {
		
		return "";
	}
}
