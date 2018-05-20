package modelo.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;

import controlador.ConexionManager;
import modelo.POJOs.CuentasAcceso;

public class MetodosCuentasAcceso {
	
	public static HashMap<String,CuentasAcceso> cuentasUsuario= new HashMap<String,CuentasAcceso>();
	
	public void cargarCuentasAcceso() throws SQLException {
		
		String nomUsuario;
		String pass;
		
		ConexionManager conManager = new ConexionManager();
		Connection conexion = conManager.crear();
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT nombreAcceso,contrasenaAdministrador FROM cuenta_administrador");
			ResultSet resultado = consulta.executeQuery();
			
			while(resultado.next()) {
				
				nomUsuario=resultado.getString("nombreAcceso");
				pass = resultado.getString("contrasenaAdministrador");
				
				cuentasUsuario.put(nomUsuario, new CuentasAcceso(nomUsuario,pass));
			}
			
			conManager.cerrar();
			
		}catch(Exception e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}
	}
	
	
	public boolean nuevaCuentaUsuario(String nomUs, String pass1, String pass2) throws SQLException {
		
		if (nomUs == null || pass1 == null || pass2 == null) {
			
			JOptionPane.showMessageDialog(null, "Introduce valores validos");
			return false;
			
		}else{
			
			cargarCuentasAcceso();
			
			try {
				
				if (pass1.equals(pass2)) {
					
					if(MetodosGenerales.encuentraKeyStringHashMap(cuentasUsuario, nomUs)) {
						
						JOptionPane.showMessageDialog(null, "Nombre de usuario ya registrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						return false;
						
					}else{
						
						ConexionManager conManager = new ConexionManager();
						Connection conexion = conManager.crear();
						
						PreparedStatement consulta = conexion.prepareStatement("INSERT INTO cuenta_administrador (nombreAcceso, contrasenaAdministrador) VALUES (?, ?)");
						
						consulta.setString(1, nomUs);
						consulta.setString(2, pass1);
						
						consulta.execute();
						conManager.cerrar();
						
						cargarCuentasAcceso();
						
						//cuentasUsuario.put(nomUs, new CuentasAcceso(nomUs,pass1));
						return true;
						
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Las contrase�as deben coincidir", "Informacion", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}catch(Exception e) {
				System.out.println("Excepcion no controlada");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean iniciarSesion(String nombre, String pass) throws SQLException {
		
		cargarCuentasAcceso();
		
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
