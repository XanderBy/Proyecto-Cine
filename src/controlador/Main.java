package controlador;

import modelo.POJOs.Funcion;
import vista.IniciarSesion;
import vista.PantallaAdministrador;
import vista.PantallaUsuario;

public class Main {

	public static void main(String[] args) {
		/*
		 * IniciarSesion is=new IniciarSesion(); is.setVisible(true);
		 */
		//new ControladorAcceso(new IniciarSesion()).Iniciar();
		//new ControladorCine(new PantallaAdministrador()).Iniciar();
		new ControladorUsuario(new PantallaUsuario()).Iniciar();
		new Funcion().start();
		/*
		 * CrearUsuario cu = new CrearUsuario(); cu.setVisible(true);
		 * cu.setLocationRelativeTo(null);
		 * 
		 * PantallaUsuario pu=new PantallaUsuario(); pu.setVisible(true);
		 * pu.setLocationRelativeTo(null);
		 */

	}

}
