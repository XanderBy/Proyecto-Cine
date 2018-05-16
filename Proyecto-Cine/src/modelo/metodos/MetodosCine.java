package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	        String q = " DELETE FROM cine WHERE  nombreCine='" + id + "' " ;
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
		System.out.println("prueba");
		 DefaultTableModel tablemodel = new DefaultTableModel();
	      int registros = 0;
	      String[] columNames = {"Nombre","dw3a"};
	      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
	      //para formar la matriz de datos
	      try{
	         PreparedStatement pstm = this.conex.prepareStatement( "SELECT count(*) as total FROM cine");
	         ResultSet res = pstm.executeQuery();
	         res.next();
	         registros = res.getInt("total");
	         res.close();
	      }catch(SQLException e){
	         System.err.println( e.getMessage() );
	      }
	    //se crea una matriz con tantas filas y columnas que necesite
	    Object[][] data = new String[registros][5];
	      try{
	          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
	         PreparedStatement pstm = this.conex.prepareStatement("SELECT nombre FROM cine");
	         ResultSet res = pstm.executeQuery();
	         int i=0;
	         while(res.next()){
	                data[i][0] = res.getString( "nombreCine" );
	            i++;
	         }
	         res.close();
	         //se añade la matriz de datos en el DefaultTableModel
	         tablemodel.setDataVector(data, columNames );
	         }catch(SQLException e){
	            System.err.println( e.getMessage() );
	        }
	        return tablemodel;
		
	}

}