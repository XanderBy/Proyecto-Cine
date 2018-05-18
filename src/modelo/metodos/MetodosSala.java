package modelo.metodos;

import java.util.HashMap;
import modelo.POJOs.Sala;

import javax.swing.JOptionPane;

import modelo.POJOs.Cine;

public class MetodosSala {
	
    public static HashMap <String, Sala>Salas=new HashMap <String, Sala>();
	//CONSTRUCTOR DE CLASE
	
	public MetodosSala(){
		
	}
	
	public Sala crearSala(String auditoriumCineId, int seatsNumber) {
		Sala s = null;
		try {
			if (auditoriumCineId==null) {
				JOptionPane.showMessageDialog(null, "Introduzca datos validos");
			}
			else {
				s=new Sala(auditoriumCineId, seatsNumber);
				JOptionPane.showMessageDialog(null, "Sala creada correctamente");
			}
		} catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
		return s;
	}
	
	public void eliminarSala(String auditoriumCineId) {
		
		//if(Cine.salasCine.containsKey(auditoriumCineId)) {
		//	Cine.salasCine.remove(auditoriumCineId);
		//}
		//else {
		//	System.out.println("No existe la sala");
		//}
		
	}

	

}
