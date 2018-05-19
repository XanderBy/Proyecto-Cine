package modelo.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import controlador.ConexionManager;
import modelo.POJOs.Promocion;

public class MetodosPromocion {

	public static HashMap<Integer, Promocion> mapPromocionesCreadas = new HashMap<Integer, Promocion>();

	// CONSTRUCTOR: Vacio

	public MetodosPromocion() {

	}

	// METODO: Crear promocion. Recibe la decsripcion de la promo y el descuento. Las inserta en el map y en la base de datos.
	public void crearPromocion(int promoDiscount,String promoDescription) {
		Promocion p = null;
		
		try {
			if (promoDescription == null || promoDiscount == 0) {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
				
			}else if(mapPromocionesCreadas.containsKey(promoDiscount)){
				JOptionPane.showMessageDialog(null, "Ya existe una promocion con ese descuento");
			}
			else {
				p = new Promocion(promoDescription, promoDiscount);
				mapPromocionesCreadas.put(promoDiscount, p);
				
				// Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos el metodo crear()
				ConexionManager conexionManager = new ConexionManager();
				Connection conexion = conexionManager.crear();

				// Realizamos la insercion
				
				//1.Creamos el PreparedStatement: Consulta, con valores desconocidos
				PreparedStatement preparedStatement = conexion.prepareStatement("INSERT INTO PROMOCION (DESCUENTOPROMO, DESCRIPCIONPROMO) VALUES (?, ?)");
				//2.Decimos que en el valor desconocido 1 inserte el valor del String promoDiscount
				preparedStatement.setInt(1, promoDiscount);
				//3.Decimos que en el valor desconocido 2 inserte el valor del String promoDescription
				preparedStatement.setString(2, promoDescription);
				//4.Ejecutamos el preparedStatement
				preparedStatement.execute();
				//5.Cerramos la conexion
				conexionManager.cerrar();
				//6.Informamos
				JOptionPane.showMessageDialog(null, "Promocion creada correctamente");

			}

		} catch (Exception e) {
			System.err.println("Excepcion no controlada al crear promocion");
			e.printStackTrace();
		}
	}
	
	
	//	METODO: Obtener las claves primarias (descuento) de las promociones insertadas en la BBDD 
	
	public ArrayList<Integer> obtenerClavesPrimariasPromocion() throws SQLException {
		
		ArrayList<Integer> clavesPrimariasPromocion = new ArrayList<Integer>();
		
		// Creamos la conexion: Instanciamos objeto de ConexionManager e invocamos el metodo crear()
		ConexionManager conexionManager = new ConexionManager();
		Connection conexion = conexionManager.crear();

		//1.Creamos el Statement
		PreparedStatement consulta = conexion.prepareStatement("SELECT DESCUENTOPROMO FROM PROMOCION");
		//2.Preparamos el ResultSet
		ResultSet resultado=consulta.executeQuery();
		//3.Iteramos sobre las tuplas de la base de datos
		while(resultado.next()) {
			clavesPrimariasPromocion.add(resultado.getInt("DESCUENTOPROMO"));
		}
		
		//Cerramos la conexion
		conexionManager.cerrar();
		
		return clavesPrimariasPromocion;
		
	}
	
	//LEER PROMOCIONES
	public Object[][] leerPromociones() {
		try {
			Object[][] resultado;

			ConexionManager conexionManager = new ConexionManager();
			Connection conexion = conexionManager.crear();

			PreparedStatement count = conexion.prepareStatement("SELECT COUNT(*) AS NUM_PROMOCIONES FROM PROMOCION");
			ResultSet rs1 = count.executeQuery();
			int numeroFilas = rs1.getInt(1);

			resultado = new Object[numeroFilas][2];

			PreparedStatement consulta = conexion.prepareStatement("SELECT * FROM PROMOCION");
			ResultSet rs2 = consulta.executeQuery();

			int i = 0;
			while (rs2.next()) {
				String descripcionPromo = rs2.getString("DESCRIPCIONPROMO");
				int descuentoPromo = rs2.getInt("DESCUENTOPROMO");
				resultado[i][0] = descripcionPromo;
				resultado[i][1] = descuentoPromo;
				i++;
			}

			conexionManager.cerrar();
			
			return resultado;

		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			//Aqui se debe relanzar una excepcion ohacer algo
			return null;
		}
	}

	// Metodo: Modificar promocion logica

	public void modificarPromocionLogica(String oldPromoDescription, String newPromoDescription, int oldPromoDiscount,
			int newPromoDiscount) {
		Promocion p = null;
		try {
			if (newPromoDescription == null || newPromoDiscount == 0) {
				JOptionPane.showMessageDialog(null, "Debe introducir un nuevo descuento y una nueva descripcion");
			} else {
				if (mapPromocionesCreadas.containsKey(oldPromoDiscount)) {
					mapPromocionesCreadas.remove(oldPromoDiscount);
					p = new Promocion(newPromoDescription, newPromoDiscount);
					mapPromocionesCreadas.put(newPromoDiscount, p);
					System.out.println("Promocion modificada en la logica del programa");
				} else {
					System.out.println("La promocion que intenta modificar ya no existe");
				}
			}
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
	}
	
	//REVISAR METODO
	public void leerPromocionesBBDD() {
		MetodosPromocion mp=new MetodosPromocion();
		mp.leerPromociones();
	}

	// ELIMINAR PROMOCION

	public void EliminarPromocion(Promocion p, int promoDiscount) {
		try {
			if (mapPromocionesCreadas.containsKey(promoDiscount)) {
				mapPromocionesCreadas.remove(promoDiscount);
				JOptionPane.showMessageDialog(null, "Promocion eliminada");
			} else {
				JOptionPane.showMessageDialog(null, "No existe la promocion");
			}
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
	}

}
