package modelo.POJOs;

import java.util.HashMap;
import java.util.Iterator;

public class Compagnia {
	
	private String nombreCompagnia;
	
	public static HashMap<String,Cine> listaCines;
	
	public Compagnia(String nomComp) {
		
		nombreCompagnia = nomComp;
		
		listaCines = new HashMap<String,Cine>();
		
	}
	
	public String getNombreCompagnia() {
		
		return nombreCompagnia;
		
	}
	
	public void setNombreCompagnia(String nomComp) {
		
		nombreCompagnia = nomComp;
		
	}
	
	public HashMap<String,Cine> getListaCines(){
		
		return listaCines;
		
	}
	
	public void setListaCines (HashMap<String,Cine> listaCines) {
		
		this.listaCines=listaCines;
		
	}
	
	public String listarCines() {
		
		Iterator it = listaCines.keySet().iterator();
		
		String a="";
		
		while(it.hasNext()){
			
		  String key = (String) it.next();
		  
		  a = a + listaCines.get(key);
		  
		}
		
		return a;
		
	}
	
	public String toString() {
		
		return "\nNombre de la Compañia: " + nombreCompagnia + "\nListado de Cines: " + listarCines();
		
	}

}
