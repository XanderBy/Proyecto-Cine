package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import modelo.POJOs.CalificacionEdades;
import modelo.POJOs.Compagnia;
import modelo.POJOs.GeneroPelicula;
import modelo.POJOs.IdiomaOriginal;
import modelo.POJOs.Pais;
import modelo.POJOs.ValoracionPeli;
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
		
		//para introducir items en los jcombobox
		for (GeneroPelicula a : GeneroPelicula.values()) {	
			pantallaAdministrador.jComboBox1.addItem(a.name());
		}
		
		for (CalificacionEdades a : CalificacionEdades.values()) {
			pantallaAdministrador.jComboBox3.addItem(a.name());
		}
		for (IdiomaOriginal a : IdiomaOriginal.values()) {
			pantallaAdministrador.jComboBox2.addItem(a.name());
		}
		for (Pais a : Pais.values()) {
			pantallaAdministrador.jComboBox11.addItem(a.name());
			pantallaAdministrador.jComboBox4.addItem(a.name());
			pantallaAdministrador.jComboBox5.addItem(a.name());
		}
		for (ValoracionPeli a : ValoracionPeli.values()) {
			
		}
		
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
				String sitioWeb = pantallaAdministrador.jTextField32.getText();
				Duration duracionPelicula = Duration.ofMinutes(Integer.parseInt(pantallaAdministrador.jTextField33.getText()));
				String calificacionEdades = pantallaAdministrador.jComboBox3.getName();
				LocalDate fechaEstrenoEs = LocalDate.parse(pantallaAdministrador.jTextField49.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				String resumen = pantallaAdministrador.jTextField35.getText();
				int idPelicula = Integer.parseInt(pantallaAdministrador.jTextField34.getText());
				
				metodosPelicula.agnadirPelicula(agnoProduccion, tituloDistribucion, tituloOriginal, genero, idioma, subtitulosEs, paisOrigen, sitioWeb, duracionPelicula, calificacionEdades, fechaEstrenoEs, resumen, idPelicula);
				
				pantallaAdministrador.jTextField28.setText("");
				pantallaAdministrador.jTextField29.setText("");
				pantallaAdministrador.jTextField30.setText("");
				pantallaAdministrador.jTextField32.setText("");
				pantallaAdministrador.jTextField33.setText("");
				pantallaAdministrador.jTextField49.setText("");
				pantallaAdministrador.jTextField35.setText("");
				pantallaAdministrador.jTextField34.setText("");
				
			} catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Debe introducir valores correctos");
            } catch (SQLException e1) {
				System.err.println("Error SQL");
				e1.printStackTrace();
			}
			break;
			
		case MODIFICAR_PELICULA:
			
			
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
