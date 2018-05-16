package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.metodos.MetodosArtistas;
import modelo.metodos.MetodosCine;
import vista.PantallaAdministrador;

public class ControladorCine implements ActionListener {// Esta Clase es una prueba
	private PantallaAdministrador pantallaAdministrador;
	private MetodosArtistas metodosArtistas;
	private MetodosCine metodosCine;

	public enum AccionMVC {// Aqui van las opciones
		ELIMINAR_CINE, ANIADIR_CINE, MODIFICAR_CINE, ELEGIR_CINE
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

		// eliminar cine
		this.pantallaAdministrador.jButton4.setActionCommand("ELIMINAR_CINE");
		this.pantallaAdministrador.jButton4.addActionListener(this);

		// Añadir Cine
		this.pantallaAdministrador.jButton3.setActionCommand("ANIADIR_CINE");
		this.pantallaAdministrador.jButton3.addActionListener(this);

		// Elegir Cine
		this.pantallaAdministrador.jButton2.setActionCommand("ELEGIR_CINE");
		this.pantallaAdministrador.jButton2.addActionListener(this);

		// Modificar Cine
		this.pantallaAdministrador.jButton6.setActionCommand("MODIFICAR_CINE");
		this.pantallaAdministrador.jButton6.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (AccionMVC.valueOf(e.getActionCommand())) {
		case ANIADIR_CINE:
			// Aqui iría los metodos

			metodosCine.crearCine(pantallaAdministrador.jTextField1.getText(),
					pantallaAdministrador.jTextField2.getText(),
					Integer.valueOf(pantallaAdministrador.jTextField3.getText()),
					Integer.valueOf(pantallaAdministrador.jTextField4.getText()));
			System.out.println("Hola");
			break;
		case ELEGIR_CINE:
			//pantallaAdministrador.jList1.getSelectedValues()
			break;
		case MODIFICAR_CINE:
			break;
		}

	}

}
