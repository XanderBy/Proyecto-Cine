package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import modelo.metodos.MetodosCine;
import modelo.metodos.MetodosPromocion;
import vista.PantallaAdministrador;

public class ControladorCine implements ActionListener, MouseListener {

	// Instanciamos vista PantallaAdministrador
	public PantallaAdministrador pantallaAdministrador = new PantallaAdministrador();
	// Instanciamos modelos
	public MetodosCine metodosCine = new MetodosCine();
	public MetodosPromocion metodosPromocion=new MetodosPromocion();
	// Declaramos en un enum las acciones relacionadas con el Administrador
	public enum accionesAdministrador {
		ELIMINAR_CINE, ANIADIR_CINE, MODIFICAR_CINE, ELEGIR_CINE, 
		//ELIMINAR_FUNCION, ANIADIR_FUNCION, MODIFICAR_FUNCION, ELEGIR_FUNCION,//Este ultimo dudo
		CREAR_PROMOCION, MODIFICAR_PROMOCION, ELIMINAR_PROMOCION, 
		ANIADIR_SALA, MODIFICAR_SALA
	}

	// CONSTRUCTOR DE CLASE

	public ControladorCine(PantallaAdministrador pantallaAdministrador) {
		super();
		this.pantallaAdministrador = pantallaAdministrador;
	}

	// INICIAMOS

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
		
		//Declaramos las acciones y aniadimos las escuchas al evento producido por el componente
		//Cine
		this.pantallaAdministrador.jTable7.addMouseListener(this);
		this.pantallaAdministrador.jTable7.setModel(new DefaultTableModel());
		//Promocion
		
		
		// Eliminar cine
		this.pantallaAdministrador.jButton4.setActionCommand("ELIMINAR_CINE");
		this.pantallaAdministrador.jButton4.addActionListener(this);
		
		// Aniadir Cine
		this.pantallaAdministrador.jButton3.setActionCommand("ANIADIR_CINE");
		this.pantallaAdministrador.jButton3.addActionListener(this);

		// Elegir Cine
		this.pantallaAdministrador.jButton2.setActionCommand("ELEGIR_CINE");
		this.pantallaAdministrador.jButton2.addActionListener(this);

		// Modificar Cine
		this.pantallaAdministrador.jButton6.setActionCommand("MODIFICAR_CINE");
		this.pantallaAdministrador.jButton6.addActionListener(this);
		
		// Crear promocion
		pantallaAdministrador.botonAniadirPromocion.setActionCommand("CREAR_PROMOCION");
		pantallaAdministrador.botonAniadirPromocion.addActionListener(this);
		pantallaAdministrador.botonAniadirPromocion.addMouseListener(this);
		
		// Modificar promocion
		pantallaAdministrador.botonModificarPromocion.setActionCommand("MODIFICAR_PROMOCION");
		pantallaAdministrador.botonModificarPromocion.addActionListener(this);
		pantallaAdministrador.botonModificarPromocion.addMouseListener(this);
		
		// Eliminar promocion
		pantallaAdministrador.botonEliminarPromocion.setActionCommand("ELIMINAR_PROMOCION");
		pantallaAdministrador.botonEliminarPromocion.addActionListener(this);
		pantallaAdministrador.botonEliminarPromocion.addMouseListener(this);

		pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDDNombre());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (accionesAdministrador.valueOf(e.getActionCommand())) {
		case ANIADIR_CINE:
			int numero=0;
			double precioBase=0;
			String nombreCine = pantallaAdministrador.jTextField1.getText();
			System.out.println(nombreCine);
			String direccion = pantallaAdministrador.jTextField2.getText();
			if(pantallaAdministrador.jTextField3.getText().length()==0) {
				numero=0;
			}else {
				numero = Integer.parseInt(pantallaAdministrador.jTextField3.getText());
			}
			if(pantallaAdministrador.jTextField4.getText().length()==0) {
				precioBase=0;
			}else {
				precioBase = Double.parseDouble(pantallaAdministrador.jTextField4.getText());
			}
			

			metodosCine.crearCine(nombreCine, direccion, numero, precioBase);
			break;
		case ELIMINAR_CINE:
			pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDDNombre());
			// pantallaAdministrador.jList1.getSelectedValues()
			System.out.print("dwadaw");
			break;
		case MODIFICAR_CINE:
			break;
		case ELEGIR_CINE:
			break;
		case CREAR_PROMOCION:
			try {
				String descripcionPromo=pantallaAdministrador.textoDescripcionPromocionAniadir.getText();
				int descuentoPromo=Integer.parseInt(pantallaAdministrador.textoDescuentoPromocionAniadir.getText());
				metodosPromocion.crearPromocion(descripcionPromo, descuentoPromo);
				System.out.println("Ok controlador");
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Debe introducir valores correctos");
			}
			break;
		case MODIFICAR_PROMOCION:
			 
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
