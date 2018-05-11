package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.metodos.MetodosArtistas;
import vista.PantallaAdministrador;

public class Controlador implements ActionListener {//Esta Clase es una prueba
	private PantallaAdministrador pantallaAdministrador;
	private MetodosArtistas metodosArtistas;

	public Controlador(PantallaAdministrador pantallaAdministrador, MetodosArtistas metodosArtistas) {
		super();
		this.pantallaAdministrador = pantallaAdministrador;
		this.metodosArtistas = metodosArtistas;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
