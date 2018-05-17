package modelo.metodos;

import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.POJOs.CuentasAcceso;

public class MetodosCuentasAcceso {
	
	public static HashMap<String,CuentasAcceso> cuentasUsuario= new HashMap<String,CuentasAcceso>();
	
	public boolean nuevaCuentaUsuario(String nomUs, String pass1, String pass2) {
		
		try {
			
			if (pass1.equals(pass2)) {
				
				if(MetodosGenerales.encuentraKeyStringHashMap(cuentasUsuario, nomUs)) {
					cuentasUsuario.put(nomUs, new CuentasAcceso(nomUs,pass1));
					return true;
				}else{
					JOptionPane.showMessageDialog(null, "Nombre de usuario ya registrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}

			}else{
				JOptionPane.showMessageDialog(null, "Cuenta no creada", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}catch(Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean iniciarSesion(String nombre, String pass) {
		
		try {
			if (MetodosGenerales.encuentraKeyStringHashMap(cuentasUsuario, nombre)) {
				if (cuentasUsuario.get(nombre).getContrasegnaAdminUsuario().equals(pass)) {
					return true;
				}else{
					JOptionPane.showMessageDialog(null, "Contrasegna Incorrecta", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}else{
				JOptionPane.showMessageDialog(null, "Nombre de usuario no encontrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			
		}catch(Exception e) {
			System.out.println("Excepcion no controlada");
			e.printStackTrace();
			return false;
		}
	}
}
