package modelo.metodos;

import java.util.HashMap;

import javax.swing.JOptionPane;

import modelo.POJOs.Promocion;

public class MetodosPromocion {
	
	//DECLARAMOS HASHMAP

	public static HashMap<Integer, Promocion> mapPromocionesCreadas = new HashMap<Integer, Promocion>();

	// CONSTRUCTOR DE CLASE

	public MetodosPromocion() {

	}

	// CREAR PROMOCION

	public void crearPromocion(String promoDescription, int promoDiscount) {
		Promocion p = null;
		try {
			if (promoDescription == "") {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
				System.out.println("Ok modelo1");//Eliminar
			} else {
				p = new Promocion(promoDescription, promoDiscount);
				mapPromocionesCreadas.put(promoDiscount, p);
				JOptionPane.showMessageDialog(null, "Promocion creada correctamente");
				System.out.println("Ok modelo2");//Eliminar
			}

		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
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
