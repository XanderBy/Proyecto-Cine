package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import modelo.POJOs.Compagnia;
import modelo.POJOs.ValoracionPeli;
import modelo.metodos.MetodosCine;
import modelo.metodos.MetodosCuentasAcceso;
import modelo.metodos.MetodosEntrada;
import modelo.metodos.MetodosFuncion;
import modelo.metodos.MetodosOpinion;
import vista.PantallaUsuario;

public class ControladorUsuario implements ActionListener, MouseListener{

	//Instanciamos vista PantallaUsuario
	public PantallaUsuario pantallaUsuario = new PantallaUsuario();
	//Instanciamos modelos
	public MetodosCuentasAcceso metodosCuentasAcceso = new MetodosCuentasAcceso();
	public MetodosOpinion metodosOpinion = new MetodosOpinion();
	public MetodosEntrada metodosEntrada = new MetodosEntrada();
	public MetodosCine metodosCine = new MetodosCine();
	public MetodosFuncion metodosFuncion = new MetodosFuncion();
	//Declaracion de variables
	
	//Declaramos en un enum las acciones relacionadas con los usuarios
	public enum accionesUsuario{
		ENTRAR,VOLVER1,VOLVER2,SALIR,CANCELAR,ENVIAR,COMPRAR,
		//TODO
	}
	
	//CONSTRUCTOR DE CLASE
	public ControladorUsuario(PantallaUsuario pantallaUsuario) {
		this.pantallaUsuario = pantallaUsuario;
	}
	
	//INICIAMOS
	public void Iniciar() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(pantallaUsuario);
			pantallaUsuario.setVisible(true);
			pantallaUsuario.setLocationRelativeTo(null);
		} catch (UnsupportedLookAndFeelException ex) {
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		}
		//CARTELERA
		this.pantallaUsuario.jTable1.addMouseListener(this);
		this.pantallaUsuario.jTable1.setModel(new DefaultTableModel());
		
		this.pantallaUsuario.jTable3.addMouseListener(this);
		this.pantallaUsuario.jTable3.setModel(new DefaultTableModel());
		
		//Declaramos las acciones y escuchas al evento producido por el
		//componente
		
		//TODO añadir acciones y escuchas
		pantallaUsuario.jButton1.setActionCommand("ENTRAR");
		pantallaUsuario.jButton1.addActionListener(this);
		pantallaUsuario.jButton6.setActionCommand("VOLVER1");
		pantallaUsuario.jButton6.addActionListener(this);
		pantallaUsuario.jButton2.setActionCommand("ENVIAR");
		pantallaUsuario.jButton2.addActionListener(this);
		pantallaUsuario.jButton7.setActionCommand("CANCELAR");
		pantallaUsuario.jButton7.addActionListener(this);
		pantallaUsuario.jButton8.setActionCommand("SALIR");
		pantallaUsuario.jButton8.addActionListener(this);
		pantallaUsuario.jButton4.setActionCommand("COMPRAR");
		pantallaUsuario.jButton4.addActionListener(this);
		pantallaUsuario.jButton5.setActionCommand("VOLVER2");
		pantallaUsuario.jButton5.addActionListener(this);
		pantallaUsuario.ComprarEntrada.setLocationRelativeTo(null);
		pantallaUsuario.ConsultarCompras.setLocationRelativeTo(null);
		pantallaUsuario.AgnadirOpinion.setLocationRelativeTo(null);
		pantallaUsuario.ConsultarCartelera.setLocationRelativeTo(null);
		
		pantallaUsuario.jTable1.setModel(metodosCine.cogerCineBBDDTodo());
		//para introducir items en los jcombobox
		for (ValoracionPeli a : ValoracionPeli.values()) {
			pantallaUsuario.jComboBox6.addItem(a.name());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (accionesUsuario.valueOf(e.getActionCommand())) {
		case ENTRAR:
			if (pantallaUsuario.jRadioButton1.isSelected()) {
				
				pantallaUsuario.setVisible(false);
				pantallaUsuario.ConsultarCartelera.setVisible(true);
				
			}else if (pantallaUsuario.jRadioButton2.isSelected()) {
				
				pantallaUsuario.setVisible(false);
				pantallaUsuario.AgnadirOpinion.setVisible(true);
				
			}else if (pantallaUsuario.jRadioButton3.isSelected()) {
				
				pantallaUsuario.setVisible(false);
				pantallaUsuario.ComprarEntrada.setVisible(true);
				pantallaUsuario.jTable4.setModel(metodosEntrada.generarTablaEntradas());
				
			}else if (pantallaUsuario.jRadioButton4.isSelected()) {
				
				pantallaUsuario.setVisible(false);
				pantallaUsuario.ConsultarCompras.setVisible(true);
			}
			break;
		case VOLVER1:
			pantallaUsuario.setVisible(true);
			pantallaUsuario.ConsultarCartelera.setVisible(false);
			break;
		case ENVIAR:
			//TODO
			
			
			
			pantallaUsuario.setVisible(true);
			pantallaUsuario.AgnadirOpinion.setVisible(false);
			break;
			
		case CANCELAR:
			pantallaUsuario.setVisible(true);
			pantallaUsuario.AgnadirOpinion.setVisible(false);
			break;
			
		case SALIR:
			pantallaUsuario.setVisible(true);
			pantallaUsuario.ComprarEntrada.setVisible(false);
			break;
			
		case COMPRAR:
			//TODO
			
			pantallaUsuario.setVisible(true);
			pantallaUsuario.ComprarEntrada.setVisible(false);
			break;
			
		case VOLVER2:
			pantallaUsuario.setVisible(true);
			pantallaUsuario.ConsultarCompras.setVisible(false);
			break;
		default:
			break;
		}
	}
	
	private void presionarJTable1(java.awt.event.MouseEvent e) {

		if (e.getButton() == 1)// boton izquierdo
		{
			int fila = this.pantallaUsuario.jTable1.rowAtPoint(e.getPoint());
			if (fila > -1) {
				//pantallaUsuario.jLabel104
					//	.setText(String.valueOf(this.pantallaUsuario.jTable1.getValueAt(fila, 0)));
				metodosFuncion.cogerFuncionBBDDCine(Compagnia.listaCines
						.get(String.valueOf(this.pantallaUsuario.jTable1.getValueAt(fila, 0))));

				pantallaUsuario.jTable3.setModel(metodosFuncion.cogerFuncionBBDDCine(Compagnia.listaCines
						.get(String.valueOf(this.pantallaUsuario.jTable1.getValueAt(fila, 0)))));

			}
		}
	}
	private void presionarJTable3(java.awt.event.MouseEvent e) {

		if (e.getButton() == 1)// boton izquierdo
		{
			int fila = this.pantallaUsuario.jTable3.rowAtPoint(e.getPoint());
			if (fila > -1) {
				pantallaUsuario.jTextField3.setText(String.valueOf(this.pantallaUsuario.jTable1.getValueAt(fila, 0)));
				pantallaUsuario.jTextField4.setText(String.valueOf(this.pantallaUsuario.jTable1.getValueAt(fila, 1)));
				pantallaUsuario.jTextField5.setText(String.valueOf(this.pantallaUsuario.jTable1.getValueAt(fila, 2)));
				//pantallaUsuario.jTextField6.setText(String.valueOf(this.pantallaUsuario.jTable1.getValueAt(fila, 3)));
				
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		presionarJTable1(e);
		presionarJTable3(e);
		
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
