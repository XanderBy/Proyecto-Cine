package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import modelo.POJOs.Compagnia;
import modelo.metodos.MetodosCine;
import modelo.metodos.MetodosFuncion;
import modelo.metodos.MetodosPelicula;
import modelo.metodos.MetodosPromocion;
import modelo.metodos.MetodosSala;
import vista.PantallaAdministrador;

public class ControladorTempAntonio implements ActionListener, MouseListener{

	public PantallaAdministrador pantallaAdministrador = new PantallaAdministrador();
	// Instanciamos modelos
	public MetodosFuncion metodosFuncion = new MetodosFuncion();
	public MetodosCine metodosCine = new MetodosCine();
	public MetodosPromocion metodosPromocion = new MetodosPromocion();
	public MetodosSala metodosSala = new MetodosSala();
	public MetodosPelicula metodosPelicula = new MetodosPelicula();
	
	
	public ControladorTempAntonio(PantallaAdministrador pantallaAdministrador) {
		this.pantallaAdministrador= pantallaAdministrador;
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
		
	}
	
	public void actionPerformed (ActionEvent e) {
		//para presionar botones
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}
	
}
