package modelo.metodos;

import java.time.LocalDate;

import controlador.ConexionManager;
import javax.swing.JOptionPane;
import modelo.POJOs.CuentasAcceso;
import modelo.POJOs.Opinion;
import modelo.POJOs.ValoracionPeli;

public class MetodosOpinion extends ConexionManager {
	public void hacerOpinion(LocalDate fechaOpinion, String comentario, int idPelicula, ValoracionPeli valoracion,
			int edadUsuario, CuentasAcceso usuario) {
            if(fechaOpinion== null || comentario==null|| idPelicula==0 || valoracion==null|| edadUsuario==0||usuario==null){
                JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
            }else{
		Opinion opinion = new Opinion(fechaOpinion, comentario, idPelicula, valoracion, edadUsuario, usuario);
		MetodosPelicula.peliculas.get(idPelicula).opiniones.put(usuario.getNombreAdminUsuario(), opinion);
		
                JOptionPane.showMessageDialog(null, "Opinion realizada");
            }
	}

	public void insertarOpinionBBDD() {

	}

	public void cogerOpinionBBDD() {

	}
}
