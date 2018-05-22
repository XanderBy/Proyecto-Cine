package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.metodos.MetodosCuentasAcceso;
import vista.CrearUsuario;

public class ControladorCrearUsuario implements ActionListener, MouseListener{

	public CrearUsuario crearUsuario = new CrearUsuario();
	
	public MetodosCuentasAcceso metodosCuentasAcceso = new MetodosCuentasAcceso();
	
	public enum accionesCrearUsuario{
		
		//TODO hacer
	}
	
	public ControladorCrearUsuario(CrearUsuario crearUsuario) {
		this.crearUsuario = crearUsuario;
	}
	
	public void Iniciar() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(crearUsuario);
			crearUsuario.setVisible(true);
			crearUsuario.setLocationRelativeTo(null);
		} catch (UnsupportedLookAndFeelException ex) {
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		}
		
		crearUsuario.jButton2.setActionCommand("");
		crearUsuario.jButton2.addActionListener(this);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
