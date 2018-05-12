package modelo.metodos;

import modelo.POJOs.Sala;
import modelo.POJOs.Cine;

public class MetodosSala {
	
	public void crearSala(String auditoriumCineId, int seatsNumber) {
		try {
			if (auditoriumCineId==null) {
				auditoriumCineId="Introduzca valores validos";
				seatsNumber=0;
			}
			else {
				Sala s=new Sala(auditoriumCineId, seatsNumber);
			}
		} catch (Exception e) {
			System.out.println("Excepción no controlada");
			e.printStackTrace();
		}
	}
	
	public void eliminarSala(String auditoriumCineId) {
		Cine.salasCine.remove(auditoriumCineId);
	}

	

}
