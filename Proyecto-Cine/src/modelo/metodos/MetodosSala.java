package modelo.metodos;

import modelo.POJOs.Cine;
import modelo.POJOs.Sala;

public class MetodosSala {
	
		//CREAR SALA
	
		public void crearSala(Cine c, String auditoriumCineId, int seatsNumber) {
			Sala s;
			if(auditoriumCineId==null) {
				auditoriumCineId="Debe introducir un valor";
				seatsNumber=0;
			}
			s=new Sala(auditoriumCineId, seatsNumber);
			c.salasCine.put(c.getNombreCine(), s);
		}
		
		//ELIMINAR SALA
		
		public void eliminarSala(Cine c, Sala s) {
			
		}
}
