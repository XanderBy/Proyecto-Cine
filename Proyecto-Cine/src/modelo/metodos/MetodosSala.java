package modelo.metodos;

import modelo.POJOs.Sala;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import modelo.POJOs.Cine;
import modelo.POJOs.Promocion;

public class MetodosSala {
	
	//DECLARAMOS MAP
	
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
		
		if(Cine.salasCine.containsKey(auditoriumCineId)) {
			Cine.salasCine.remove(auditoriumCineId);
		}
		else {
			System.out.println("No existe la sala");
		}
		
	}
	
	public void modificarSala(String auditoriumCineId, int seatsNumber, String newAuditoriumCineId, int newSeatsNumber) {
		Sala s=null;
		try {
			if(newAuditoriumCineId==null || newSeatsNumber==0) {
				JOptionPane.showMessageDialog(null, "Debe introducir una nueva Id de sala y un nuevo numero de butacas");
			}
			else if(newAuditoriumCineId!=null && newSeatsNumber!=0) {
				if(Cine.salasCine.containsKey(auditoriumCineId)) {
					Cine.salasCine.remove(auditoriumCineId);
					s=new Sala(newAuditoriumCineId,newSeatsNumber);
					Cine.salasCine.put(newAuditoriumCineId, s);
					JOptionPane.showMessageDialog(null, "Sala modificada");
				} else {
					JOptionPane.showMessageDialog(null, "La sala que intenta modificar ya no existe");
				}
			}else {
				
			}
		} catch (HeadlessException e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
		}
	}

}
