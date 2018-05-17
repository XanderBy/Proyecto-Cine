package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import controlador.Conexion;
import javax.swing.JOptionPane;
import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;

public class MetodosCine extends Conexion{
	// Crear Cine
	public void crearCine(String nombreCine, String direccionCine, int numero, int precioBase) {
		if(nombreCine==null || direccionCine==null|| numero==0||precioBase==0){
                    JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
                }else{
		Cine cine = new Cine(nombreCine, direccionCine,numero,precioBase);
		Compagnia.listaCines.put(nombreCine, cine);
                JOptionPane.showMessageDialog(null, "Cine creado con exito");
                }
	}

	// Elimina el cine
	public void eliminarCine(String nombreCine) {
		Compagnia.listaCines.remove(nombreCine);
                JOptionPane.showMessageDialog(null, "Cine eliminado");
	}

	// modificar Cine
	public void modificarCine(String nombreCineAntiguo, String nombreCine, String direccionCine, int telefonoConsulta,
			int precioBase) {
            if(nombreCineAntiguo==null|| nombreCine==null|| direccionCine==null|| telefonoConsulta==0|| precioBase==0){
                JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
            }else{
		Compagnia.listaCines.remove(nombreCineAntiguo);
		Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta, precioBase);
		Compagnia.listaCines.put(nombreCine, cine);
                JOptionPane.showMessageDialog(null, "Cine modificado con exito");
        }
	}
	//TODO:Este metodo no funciona hay que cambiar la consulta
	public boolean eliminarCineBBDD(String id) {
		 boolean res=false;
	        //se arma la consulta
	        String q = " DELETE FROM cine WHERE  nombreCine='" + id + "' " ;
	        //se ejecuta la consulta
	         try {
	            PreparedStatement pstm = this.getConexion().prepareStatement(q);
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
              PreparedStatement pstm=null;
	      String[] columNames = {"nombre Cine"};
	      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
	      //para formar la matriz de datos
	      try{
	         pstm = getConexion().prepareStatement( "SELECT count(*) as total FROM cine");
	         ResultSet res = pstm.executeQuery();
	         res.next();
	         registros = res.getInt("total");
	         res.close();
	      }catch(SQLException e){
	         System.err.println( e.getMessage() );
	      }
	    //se crea una matriz con tantas filas y columnas que necesite
	    Object[][] data = new String[registros][1];
	      try{
	          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
	         pstm = this.getConexion().prepareStatement("SELECT nombreCine FROM cine");
	         ResultSet res = pstm.executeQuery();
	         int i=0;
	         while(res.next()){
	                data[i][0] = res.getString( "nombreCine" );
	            i++;
	         }
	         res.close();
	         //se anade la matriz de datos en el DefaultTableModel
	         tablemodel.setDataVector(data, columNames );
	         }catch(SQLException e){
	            System.err.println( e.getMessage() );
	        }
	        return tablemodel;
		
	}

}