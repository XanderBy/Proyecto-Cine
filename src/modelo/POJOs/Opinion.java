package modelo.POJOs;

import java.time.LocalDate;

public class Opinion {
	private LocalDate fechaOpinion;
	private String comentario;
	private int idPelicula;
	private ValoracionPeli valoracion;
	private CuentasAcceso usuario;
	private int edadUsuario;
	
	public Opinion(LocalDate fechaOpinion, String comentario, int idPelicula, ValoracionPeli valoracion,
			int edadUsuario, CuentasAcceso usuario) {
		super();
		this.fechaOpinion = fechaOpinion;
		this.comentario = comentario;
		this.idPelicula = idPelicula;
		this.valoracion = valoracion;
		this.edadUsuario = edadUsuario;
		this.usuario=usuario;
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
	public Integer getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(int idPelicula) {
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
	public CuentasAcceso getUsuario() {
		return usuario;
	}
	public void setUsuario(CuentasAcceso usuario) {
		this.usuario = usuario;
	}
}
