package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.metodos.MetodosArtistas;
import vista.PantallaAdministrador;

public class ControladorCine implements ActionListener {// Esta Clase es una prueba
	private PantallaAdministrador pantallaAdministrador;
	private MetodosArtistas metodosArtistas;

	public enum AccionMVC {// Aqui van las opciones
		ANADIR_DIRECTOR, ANADIR_ACTOR
	}

	public ControladorCine(PantallaAdministrador pantallaAdministrador) {
		super();
		this.pantallaAdministrador = pantallaAdministrador;
		// this.pantallaAdministrador.jButton23.addActionListener(this);
	}

	public void Iniciar() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(pantallaAdministrador);
			pantallaAdministrador.setVisible(true);
			pantallaAdministrador.setLocationRelativeTo(null);
		} catch (UnsupportedLookAndFeelException ex) {
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		}
		 //declara una acción y añade un escucha al evento producido por el componente
		this.pantallaAdministrador.jButton23.setActionCommand("ANADIR_DIRECTOR");
		this.pantallaAdministrador.jButton23.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (AccionMVC.valueOf(e.getActionCommand())) {
		case ANADIR_DIRECTOR:
			// Aqui iría los metodos
			System.out.println("Hola");
			break;
		case ANADIR_ACTOR:
			break;
		}

	}

}
