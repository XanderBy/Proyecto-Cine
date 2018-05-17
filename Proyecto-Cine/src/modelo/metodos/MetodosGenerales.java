package modelo.metodos;

import java.util.HashMap;
import java.util.Iterator;

public class MetodosGenerales {
	
	public static boolean encuentraKeyStringHashMap(HashMap list, String key) {
		
		boolean resultado=false;
		
		try {
			Iterator it = list.keySet().iterator();
			while (it.hasNext()) {
				String clave = (String) it.next();
				if (clave.equals(key)) {
					resultado=true;
				}else{
					resultado=false;
				}
			}
		}catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}
	
	public static boolean encuentraKeyIntHashMap(HashMap list, int key) {
		
		boolean resultado=false;
		
		try {
			Iterator it = list.keySet().iterator();
			while (it.hasNext()) {
				int clave = (int) it.next();
				if (clave==key) {
					resultado=true;
				}else{
					resultado=false;
				}
			}
		}catch (Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return resultado;
		}
		return resultado;
	}
}
