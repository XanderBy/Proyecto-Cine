package modelo.metodos;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.POJOs.Funcion;
import modelo.POJOs.Promocion;

public class MetodosPromocion {
	
	ArrayList<Promocion> listaPromocionesCreadas=new ArrayList<Promocion>();
	
	//CONSTRUCTOR DE CLASE
	
	public MetodosPromocion() {
		
	}
	
	//CREAR PROMOCION

	public Promocion crearPromocion(String promoDescription, int promoDiscount) {
		Promocion p = null;
		try {
			if(promoDescription==null) {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
			}
			else {
				p=new Promocion(promoDescription, promoDiscount);
				listaPromocionesCreadas.add(p);//
				JOptionPane.showMessageDialog(null, "Promocion creada correctamente");
			}
			
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		} finally {
			
		}
		
		return p;
	}
	
	//MODIFICAR PROMOCION
	
	public Promocion modificarPromocion(Promocion p,String promoDescription, int promoDiscount) {
		try {
			if(p.getDescripcionPromo()==null || p==null) {
				JOptionPane.showMessageDialog(null, "La promocion seleccionada no existe. Introduzca nuevos datos");
			}
			else {
				p.setDescripcionPromo(promoDescription);
				p.setDescuentoPromo(promoDiscount);
			}
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
		return p;
		
	}
	
	//ELIMINAR PROMOCION
	
	public void EliminarPromocion(Funcion f, Promocion p) {
		try {
			p.setDescripcionPromo("");
			p.setDescuentoPromo(0);
			f.setPromocionFuncion(p);
		} catch (Exception e) {
			System.out.println("ExcepciOn no controlada");
			e.printStackTrace();
		}
	}

}
