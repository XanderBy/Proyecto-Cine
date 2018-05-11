package modelo;

import vista.CrearUsuario;
import vista.IniciarSesion;
import vista.PantallaAdministrador;
import vista.PantallaUsuario;

public class Main {

	public static void Main(String[] args) {
		
		/*IniciarSesion is=new IniciarSesion();
		is.setVisible(true);*/
		
		PantallaAdministrador pa = new PantallaAdministrador();
        pa.setVisible(true);
        pa.setLocationRelativeTo(null);
        
        /*CrearUsuario cu = new CrearUsuario();
        cu.setVisible(true);
        cu.setLocationRelativeTo(null);
        
        PantallaUsuario pu=new PantallaUsuario();
        pu.setVisible(true);
        pu.setLocationRelativeTo(null);*/
	}

}
