package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.metodos.MetodosCuentasAcceso;
import vista.CrearUsuario;
import vista.IniciarSesion;
import vista.PantallaAdministrador;
import vista.PantallaUsuario;

public class ControladorAcceso implements ActionListener, MouseListener {

	public IniciarSesion iniciarSesion = new IniciarSesion();
	//Instanciamos metodos
	public MetodosCuentasAcceso metodosCuentasAcceso = new MetodosCuentasAcceso();
	
	//Declaracion enum de acciones
	public enum accionesAcceso {
		
		INICIAR_SESION, CREAR_USUARIO,
		
	}
	
	//Constructor
	public ControladorAcceso(IniciarSesion iniciarSesion) {
		
		this.iniciarSesion = iniciarSesion;
	}
	
	//Iniciamos
	public void Iniciar() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(iniciarSesion);
			iniciarSesion.setVisible(true);
			iniciarSesion.setLocationRelativeTo(null);
		} catch (UnsupportedLookAndFeelException ex) {
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		}
		
		//Declaramos las acciones y anidamos las escuchas al evento producido por el 
		//componente
		
		//
		
		iniciarSesion.jButton1.setActionCommand("INICIAR_SESION");
		iniciarSesion.jButton1.addActionListener(this);
		iniciarSesion.jButton2.setActionCommand("CREAR_USUARIO");
		iniciarSesion.jButton2.addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(accionesAcceso.valueOf(e.getActionCommand())) {
		
		case INICIAR_SESION:
			String nombreUsuario = iniciarSesion.jTextField1.getText();
			String pass = new String(iniciarSesion.jPasswordField1.getPassword());
			
			try {

				if (metodosCuentasAcceso.iniciarSesion(nombreUsuario, pass)) {
					
					if (pass.startsWith("6xd")) {
						
						iniciarSesion.setVisible(false);
						new ControladorCine(new PantallaAdministrador()).Iniciar();
						
					}else{
						
						new ControladorUsuario(new PantallaUsuario()).Iniciar();
						
					}
				}
				
			} catch (SQLException e1) {
				System.err.println("Excepcion no controlada");
				e1.printStackTrace();
			}
			break;
		case CREAR_USUARIO:
			
			iniciarSesion.setVisible(false);
			new ControladorCrearUsuario(new CrearUsuario()).Iniciar();
			
			break;
		default:
			System.out.println("Entra en default");
			break;
		}	
				
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
