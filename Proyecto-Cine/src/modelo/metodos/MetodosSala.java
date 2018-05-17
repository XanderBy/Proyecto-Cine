package modelo.metodos;

import modelo.POJOs.Sala;
import modelo.POJOs.Cine;

public class MetodosSala {
	
	//CONSTRUCTOR DE CLASE
	
	public MetodosSala(){
		
	}
	
	public Sala crearSala(String auditoriumCineId, int seatsNumber) {
		Sala s = null;
		try {
			if (auditoriumCineId==null) {
				auditoriumCineId="Introduzca valores validos";
				seatsNumber=0;
			}
			else {
				s=new Sala(auditoriumCineId, seatsNumber);
			}
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
		return s;
	}
	
	public void eliminarSala(String auditoriumCineId) {
		
		if(Cine.salasCine.containsKey(auditoriumCineId)) {
			Cine.salasCine.remove(auditoriumCineId);
		}
		else {
			System.out.println("No existe la sala");
		}
		
	}

	

}
