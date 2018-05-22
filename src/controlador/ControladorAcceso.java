package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.metodos.MetodosCuentasAcceso;
import vista.IniciarSesion;

public class ControladorAcceso implements ActionListener, MouseListener {

	public IniciarSesion iniciarSesion = new IniciarSesion();
	//Instanciamos metodos
	public MetodosCuentasAcceso metodosCuentasAcceso = new MetodosCuentasAcceso();
	//Declaracion de variables
	
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
		
		iniciarSesion.jButton1.setActionCommand("CREAR_USUARIO");
		iniciarSesion.jButton1.addActionListener(this);
		iniciarSesion.jButton2.setActionCommand("INICIAR_SESION");
		iniciarSesion.jButton2.addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(accionesAcceso.valueOf(e.getActionCommand())) {
		case INICIAR_SESION:
			
			String nombreUsuario = iniciarSesion.jTextField1.getText();
			String pass = iniciarSesion.jPasswordField1.getText();
			
			
		
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
