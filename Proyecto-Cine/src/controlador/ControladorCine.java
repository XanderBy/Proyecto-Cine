package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import modelo.metodos.MetodosCine;
import vista.PantallaAdministrador;

public class ControladorCine implements ActionListener, MouseListener {// Esta Clase es una prueba
	private PantallaAdministrador pantallaAdministrador;
	MetodosCine metodosCine=new MetodosCine();

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
		
		this.pantallaAdministrador.jTable7.addMouseListener(this);
                this.pantallaAdministrador.jTable7.setModel( new DefaultTableModel() );
		// eliminar cine
		this.pantallaAdministrador.jButton4.setActionCommand("ELIMINAR_CINE");
		this.pantallaAdministrador.jButton4.addActionListener(this);

		// A�adir Cine
		this.pantallaAdministrador.jButton3.setActionCommand("ANIADIR_CINE");
		this.pantallaAdministrador.jButton3.addActionListener(this);

		// Elegir Cine
		this.pantallaAdministrador.jButton2.setActionCommand("ELEGIR_CINE");
		this.pantallaAdministrador.jButton2.addActionListener(this);

		// Modificar Cine
		this.pantallaAdministrador.jButton6.setActionCommand("MODIFICAR_CINE");
		this.pantallaAdministrador.jButton6.addActionListener(this);
		
		pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDD());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (AccionMVC.valueOf(e.getActionCommand())) {
		case ANIADIR_CINE:
			// Aqui iria los metodos
			String nombreCine = pantallaAdministrador.jTextField1.getText();
			String direccion = pantallaAdministrador.jTextField2.getText();
			int numero = Integer.parseInt(pantallaAdministrador.jTextField3.getText());
			int prebioBase = Integer.parseInt(pantallaAdministrador.jTextField4.getText());
			
			metodosCine.crearCine(nombreCine, direccion, numero, prebioBase);
			break;
		case ELIMINAR_CINE:
                    pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDD());
			//pantallaAdministrador.jList1.getSelectedValues()
                    System.out.print("dwadaw");
			break;
		case MODIFICAR_CINE:
			break;
		default:
			System.out.println("error");
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
