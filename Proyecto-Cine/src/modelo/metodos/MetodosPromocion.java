package modelo.metodos;

import modelo.POJOs.Funcion;
import modelo.POJOs.Promocion;

public class MetodosPromocion {
	
	//CREAR PROMOCION

	public void crearPromocion(Funcion f, String promoDescription, int promoDiscount) {
		Promocion p;
		try {
			if(promoDescription==null) {
				promoDescription="No hay descripcion. Debe introducir valores correctos.";
				promoDiscount=0;
			}
			p=new Promocion(promoDescription, promoDiscount);
			f.setPromocionFuncion(p);
		} catch (Exception e) {
			System.out.println("Excepción no controlada");
			e.printStackTrace();
		}
	}
	
	//MODIFICAR PROMOCION
	
	public void modificarPromocion(Funcion f, Promocion p,String promoDescription, int promoDiscount) {
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
			f.setPromocionFuncion(p);
		} catch (Exception e) {
			System.out.println("Excepción no controlada");
			e.printStackTrace();
		}
		
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
