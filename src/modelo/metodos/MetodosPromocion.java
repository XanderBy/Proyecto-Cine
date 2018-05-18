package modelo.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JOptionPane;

import controlador.ConexionManager;
import modelo.POJOs.Promocion;

public class MetodosPromocion {

	public static HashMap<Integer, Promocion> mapPromocionesCreadas = new HashMap<Integer, Promocion>();

	// CONSTRUCTOR VACIO

	public MetodosPromocion() {

	}

	// CREAR PROMOCION
	public void crearPromocion(String promoDescription, int promoDiscount) {
		Promocion p = null;
		try {
			if (promoDescription == "") {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
			} else {
				p = new Promocion(promoDescription, promoDiscount);
				mapPromocionesCreadas.put(promoDiscount, p);

				ConexionManager conexionManager = new ConexionManager();
				Connection conexion = conexionManager.crear();

				// REALIZA EL INSERT
				PreparedStatement preparedStatement = conexion
						.prepareStatement("INSERT INTO PROMOCION (DESCRIPCIONPROMO, DESCUENTOPROMO) VALUES (?, ?)");
				preparedStatement.setString(1, promoDescription);
				preparedStatement.setInt(2, promoDiscount);
				preparedStatement.execute();

				conexionManager.cerrar();

				JOptionPane.showMessageDialog(null, "Promocion creada correctamente");

			}

		} catch (Exception e) {
			System.out.println("Excepcion no controlada al crear promocion");
			e.printStackTrace();
		}
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
				String descripcion = rs2.getString("DESCRIPCIONPROMO");
				int decuento = rs2.getInt("DESCUENTOPROMO");
				resultado[i][0] = descripcion;
				resultado[i][1] = decuento;
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

	// MODIFICAR PROMOCION

	public void modificarPromocion(String oldPromoDescription, String newPromoDescription, int oldPromoDiscount,
			int newPromoDiscount) {
		Promocion p = null;
		try {
			if (newPromoDescription == null || newPromoDiscount == 0) {
				JOptionPane.showMessageDialog(null, "Debe introducir un nuevo descuento y una nueva descripcion");
			} else if (newPromoDescription != null && newPromoDiscount != 0) {
				if (mapPromocionesCreadas.containsKey(oldPromoDiscount)) {
					mapPromocionesCreadas.remove(oldPromoDiscount);
					p = new Promocion(newPromoDescription, newPromoDiscount);
					mapPromocionesCreadas.put(newPromoDiscount, p);
					JOptionPane.showMessageDialog(null, "Promocion modificada");
				} else {
					JOptionPane.showMessageDialog(null, "La promocion que intenta modificar ya no existe");
				}
			} else {

			}
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
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
