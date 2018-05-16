package modelo.metodos;

import javax.swing.JOptionPane;

import modelo.POJOs.Funcion;
import modelo.POJOs.Promocion;

public class MetodosPromocion {
	
	//CONSTRUCTOR DE CLASE
	
	public MetodosPromocion() {
		
	}
	
	//CREAR PROMOCION

	public Promocion crearPromocion(String promoDescription, int promoDiscount) {
		Promocion p = null;
		try {
			if(promoDescription==null) {
				promoDescription="No hay descripcion. Debe introducir valores correctos.";
				promoDiscount=0;
			}
			p=new Promocion(promoDescription, promoDiscount);
		} catch (Exception e) {
			System.out.println("Excepción no controlada");
			e.printStackTrace();
		}
		System.out.println("Ok vista");
		return p;
	}
	
	//MODIFICAR PROMOCION
	
	public Promocion modificarPromocion(Promocion p,String promoDescription, int promoDiscount) {
		try {
			if(p.getDescripcionPromo()==null) {
				promoDescription="No hay descripcion. Debe introducir valores correctos.";
				p.setDescripcionPromo(promoDescription);
				p.setDescuentoPromo(0);
			}
			else {
				p.setDescripcionPromo(promoDescription);
				p.setDescuentoPromo(promoDiscount);
			}
		} catch (Exception e) {
			System.out.println("Excepción no controlada");
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
			System.out.println("Excepción no controlada");
			e.printStackTrace();
		}
	}

}
