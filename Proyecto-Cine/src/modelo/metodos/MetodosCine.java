package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import controlador.Conexion;
import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;

public class MetodosCine extends Conexion{
	// Crear Cine
	public void crearCine(String nombreCine, String direccionCine, int numero, int prebioBase) {
		System.out.println("Cine creado");
		
		Cine cine = new Cine(nombreCine, direccionCine,numero,prebioBase);
		Compagnia.listaCines.put(nombreCine, cine);
		
	}

	// Elimina el cine
	public void eliminarCine(String nombreCine) {
		Compagnia.listaCines.remove(nombreCine);
	}

	// modificar Cine
	public void modificarCine(String nombreCineAntiguo, String nombreCine, String direccionCine, int telefonoConsulta,
			int precioBase) {
		Compagnia.listaCines.remove(nombreCineAntiguo);
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		Compagnia.listaCines.put(nombreCine, cine);
	}
	//TODO:Este metodo no funciona hay que cambiar la consulta
	public boolean eliminarCineBBDD(String id) {
		 boolean res=false;
	        //se arma la consulta
	        String q = " DELETE FROM cine WHERE  p_id='" + id + "' " ;
	        //se ejecuta la consulta
	         try {
	            PreparedStatement pstm = this.conex.prepareStatement(q);
	            pstm.execute();
	            pstm.close();
	            res=true;
	         }catch(SQLException e){
	            System.err.println( e.getMessage() );
	        }
	        return res;
	}

	public boolean insertarCineBBDD() {
		return false;

	}

	public boolean actualizarCineBBDD() {
		return false;

	}
	public DefaultTableModel cogerCineBBDD() {
		return null;
		
	}

}