package modelo.POJOs;

public class CuentasAcceso {

	private String nombreAdminUsuario;

	private String contrasegnaAdminUsuario;

	public CuentasAcceso(String nomAdminUs, String passAdminUs) {

		nombreAdminUsuario = nomAdminUs;

		contrasegnaAdminUsuario = passAdminUs;
	}

	public String getNombreAdminUsuario() {

		return nombreAdminUsuario;

	}

	public void setNombreAdminUsuario(String nomAdminUs) {

		nombreAdminUsuario = nomAdminUs;

	}

	public String getContrasegnaAdminUsuario() {

		return contrasegnaAdminUsuario;

	}

	public void setContrasegnaAdminUsuario(String passAdminUs) {

		contrasegnaAdminUsuario = passAdminUs;
		
	}
	
	public String toString() {
		
		return "\nUsuario: " + nombreAdminUsuario + "\nContrasegna: " + contrasegnaAdminUsuario;
		
	}

}
