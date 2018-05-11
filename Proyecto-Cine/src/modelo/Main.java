package modelo;

import controlador.Controlador;
import vista.PantallaAdministrador;

public class Main {

	public static void main(String[] args) {
		/*IniciarSesion is=new IniciarSesion();
		is.setVisible(true);*/
		
        new Controlador(new PantallaAdministrador()).Iniciar();

        /*CrearUsuario cu = new CrearUsuario();
        cu.setVisible(true);
        cu.setLocationRelativeTo(null);
        
        PantallaUsuario pu=new PantallaUsuario();
        pu.setVisible(true);
        pu.setLocationRelativeTo(null);*/

	}

}
