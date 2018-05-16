package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.metodos.MetodosPromocion;
import vista.CrearUsuario;
import vista.PantallaAdministrador;

public class ControladorPromocion implements ActionListener, MouseListener {

	// Instanciamos vista PantallaAdministrador
	private PantallaAdministrador pantallaAdministrador;
	// Instanciamos modelo
	private MetodosPromocion metodosPromocion;

	// Declaramos en un enum las acciones relacionadas con Promocion
	public enum accionesPromocionAdministrador {
		CREAR_PROMOCION, MODIFICAR_PROMOCION, ELIMINAR_PROMOCION
	}

	// CONSTRUCTOR DE CLASE

	public ControladorPromocion(PantallaAdministrador pAdministrador) {
		pantallaAdministrador = pAdministrador;
	}

	// INICIAMOS

	/** Inicia el skin y las diferentes variables que se utilizan */
	public void iniciar() {
		// Skin tipo WINDOWS
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

		// Declara una acci�n y a�ade un escucha al evento producido por el componente
		pantallaAdministrador.botonCrearPromocion.setActionCommand("CREAR_PROMOCION");
		pantallaAdministrador.botonCrearPromocion.addActionListener(this);
		// Declara una acci�n y a�ade un escucha al evento producido por el componente
		pantallaAdministrador.botonModificarPromocion.setActionCommand("MODIFICAR_PROMOCION");
		pantallaAdministrador.botonModificarPromocion.addActionListener(this);
		// Declara una acci�n y a�ade un escucha al evento producido por el componente
		pantallaAdministrador.botonEliminarPromocion.setActionCommand("ELIMINAR_PROMOCION");
		pantallaAdministrador.botonEliminarPromocion.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (accionesPromocionAdministrador.valueOf(e.getActionCommand())) {
		case CREAR_PROMOCION:
			MetodosPromocion mp=new MetodosPromocion();
			PantallaAdministrador pa=new PantallaAdministrador();
			String descripcionPromo=pa.textoDescripcionPromocionAniadir.getText();
			int descuentoPromo=Integer.parseInt(pa.textoDescuentoPromocionAniadir.getText());
			mp.crearPromocion(descripcionPromo, descuentoPromo);
			break;
		default:
			break;
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent b) {
		if(b.getButton()==1) {
			//accionesPromocionAdministrador.CREAR_PROMOCION
		}

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

}