package modelo.metodos;

import java.time.LocalDate;

import modelo.POJOs.Opinion;
import modelo.POJOs.ValoracionPeli;

public class MetodosOpinion {
	public void hacerOpinion(LocalDate fechaOpinion, String comentario, String idPelicula, ValoracionPeli valoracion,
			int edadUsuario) {
		
		Opinion opinion=new Opinion(fechaOpinion, comentario, idPelicula, valoracion, edadUsuario);
		//Añadir al navegar por un array de peliculas y introducir la opinion a la pelicula
	}
}
