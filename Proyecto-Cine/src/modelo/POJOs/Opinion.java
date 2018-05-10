package modelo.POJOs;

import java.time.LocalDate;

public class Opinion {
	private LocalDate fechaOpinion;
	private String comentario;
	private String idPelicula;
	private ValoracionPeli valoracion;
	
	
	
	public Opinion(LocalDate fechaOpinion, String comentario, String idPelicula, ValoracionPeli valoracion,
			int edadUsuario) {
		super();
		this.fechaOpinion = fechaOpinion;
		this.comentario = comentario;
		this.idPelicula = idPelicula;
		this.valoracion = valoracion;
		this.edadUsuario = edadUsuario;
	}
	public LocalDate getFechaOpinion() {
		return fechaOpinion;
	}
	public void setFechaOpinion(LocalDate fechaOpinion) {
		this.fechaOpinion = fechaOpinion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(String idPelicula) {
		this.idPelicula = idPelicula;
	}
	public ValoracionPeli getValoracion() {
		return valoracion;
	}
	public void setValoracion(ValoracionPeli valoracion) {
		this.valoracion = valoracion;
	}
	public int getEdadUsuario() {
		return edadUsuario;
	}
	public void setEdadUsuario(int edadUsuario) {
		this.edadUsuario = edadUsuario;
	}
	public static int getCodigoOpinion() {
		return codigoOpinion;
	}
	public static void setCodigoOpinion(int codigoOpinion) {
		Opinion.codigoOpinion = codigoOpinion;
	}
	private int edadUsuario;
	private static int codigoOpinion;//tiene que aumentar solo
}
