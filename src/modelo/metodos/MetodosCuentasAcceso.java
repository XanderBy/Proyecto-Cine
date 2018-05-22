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
	
	public static HashMap<String,CuentasAcceso> cuentasAcceso= new HashMap<String,CuentasAcceso>();
	
	public void cargarCuentasAcceso() throws SQLException {
		
		String nomUsuario;
		String pass;
		
		ConexionManager conManager = new ConexionManager();
		Connection conexion = conManager.crear();
		
		try {
			
			PreparedStatement consulta = conexion.prepareStatement("SELECT nombreAcceso,contraseñaAdministrador FROM cuenta_administrador");
			ResultSet resultado = consulta.executeQuery();
			
			while(resultado.next()) {
				
				nomUsuario=resultado.getString("nombreAcceso");
				pass = resultado.getString("contraseñaAdministrador");
				
				cuentasAcceso.put(nomUsuario, new CuentasAcceso(nomUsuario,pass));
			}
			
			PreparedStatement consulta2 = conexion.prepareStatement("SELECT nombreAcceso,contraseñaUsuario FROM cuenta_usuario");
			ResultSet resultado2 = consulta2.executeQuery();
			
			while(resultado2.next()) {
				
				nomUsuario=resultado2.getString("nombreAcceso");
				pass=resultado2.getString("contraseñaUsuario");
				
				cuentasAcceso.put(nomUsuario, new CuentasAcceso(nomUsuario,pass));
			}
			
			conManager.cerrar();
			
		}catch(Exception e) {
			System.err.println("Excepcion no controlada");
			e.printStackTrace();
		}
	}
	
	
	public boolean nuevaCuenta(String nomUs, String pass1, String pass2) throws SQLException {
		
		if (nomUs == null || pass1 == null || pass2 == null) {
			
			JOptionPane.showMessageDialog(null, "Introduce valores validos");
			return false;
			
		}else{
			
			cargarCuentasAcceso();
			
			try {
				
				if (pass1.equals(pass2)) {
					
					if(cuentasAcceso.containsKey(nomUs)) {
						
						JOptionPane.showMessageDialog(null, "Nombre de usuario ya registrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						return false;
						
					}else{
						
						ConexionManager conManager = new ConexionManager();
						Connection conexion = conManager.crear();
						
						if (pass1.startsWith("6xd")) {
							
							PreparedStatement consulta = conexion.prepareStatement("INSERT INTO cuenta_administrador (nombreAcceso, contrasenaAdministrador) VALUES (?, ?)");
							
							consulta.setString(1, nomUs);
							consulta.setString(2, pass1);
							
							consulta.execute();
							conManager.cerrar();
							
						}else{
							
							PreparedStatement consulta = conexion.prepareStatement("INSERT INTO cuenta_usuario (nombreAcceso, contraseñaUsuario) VALUES (?, ?)");
							
							consulta.setString(1, nomUs);
							consulta.setString(2, pass1);
							
							consulta.execute();
							conManager.cerrar();
							
						}
						JOptionPane.showMessageDialog(null, "Cuenta creada correctamente");
						cargarCuentasAcceso();
						return true;
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Las contraseï¿½as deben coincidir", "Informacion", JOptionPane.INFORMATION_MESSAGE);
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
			
			if (cuentasAcceso.containsKey(nombre)) {
				if (cuentasAcceso.get(nombre).getContrasegnaAdminUsuario().equals(pass)) {
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
