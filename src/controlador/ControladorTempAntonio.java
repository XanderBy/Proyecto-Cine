package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
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
	//Variables
	//TODO A�ADIR VARIABLES PARA MODIFICAR Y ELIMINAR CON TABLAS
	
	public enum accionesAdministrador{
		
		CREAR_PELICULA, MODIFICAR_PELICULA, ELIMINAR_PELICULA, ANIADIR_ACTOR, ANIADIR_DIRECTOR;
	}
	
	// CONSTRUCTOR DE CLASE
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
		
		//Crear pelicula
		pantallaAdministrador.jButton25.setActionCommand("CREAR_PELICULA");
		pantallaAdministrador.jButton25.addActionListener(this);
		pantallaAdministrador.jButton25.addMouseListener(this);
		
	}
	
	public void actionPerformed (ActionEvent e) {
		//para presionar botones
		switch (accionesAdministrador.valueOf(e.getActionCommand())) {
		
		case CREAR_PELICULA:
			try {
				int agnoProduccion = Integer.parseInt(pantallaAdministrador.jTextField28.getText());
				String tituloDistribucion = pantallaAdministrador.jTextField29.getText();
				String tituloOriginal = pantallaAdministrador.jTextField30.getText();
				String genero = pantallaAdministrador.jComboBox1.getName();
				String idioma = pantallaAdministrador.jComboBox2.getName();
				boolean subtitulosEs = pantallaAdministrador.jCheckBox1.isSelected();
				String paisOrigen = pantallaAdministrador.jComboBox11.getName();
				String sitioWeb;
				Duration duracionPelicula;
				String calificacionEdades;
				LocalDate fechaEstrenoEs;
				String resumen;
				int idPelicula;
				
				
			} catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Debe introducir valores correctos");
            }
		}
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
