package modelo.metodos;

import java.time.LocalDate;

import controlador.Conexion;
import modelo.POJOs.CuentasAcceso;
import modelo.POJOs.Opinion;
import modelo.POJOs.ValoracionPeli;

public class MetodosOpinion extends Conexion {
	public void hacerOpinion(LocalDate fechaOpinion, String comentario, int idPelicula, ValoracionPeli valoracion,
			int edadUsuario, CuentasAcceso usuario) {

		Opinion opinion = new Opinion(fechaOpinion, comentario, idPelicula, valoracion, edadUsuario, usuario);
		MetodosPelicula.peliculas.get(idPelicula).opiniones.put(usuario.getNombreAdminUsuario(), opinion);
		// Anadir al navegar por un array de peliculas y introducir la opinion a la
		// pelicula
	}

	public void insertarOpinionBBDD() {

	}

	public void cogerOpinionBBDD() {

	}
}
