package modelo.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.table.DefaultTableModel;

import controlador.ConexionManager;

public class MetodosEntrada {

	//TODO hacer
	
	public DefaultTableModel generarTablaEntradas() {
		
		DefaultTableModel tablaEntradas = new DefaultTableModel();
		Object [][] resultado;
		String [] nombreColumnas = { "titulo","nombreCine","nombreSala","diayHora","promocion"};
		
		try {
			
			ConexionManager conManager = new ConexionManager();
			Connection conexion = conManager.crear();
		
			PreparedStatement consulta = conexion.prepareStatement("SELECT count(*) as numfilas" + 
					"FROM pelicula p, peliculasala ps, sala s, funcion f, funcionpromocion fp WHERE p.idPelicula=ps.pelicula_idPelicula AND ps.sala_idSalaCine=s.idSalaCine and"
					+ " s.idSalaCine=f.sala_idSalaCine and f.diayHora=fp.funcion_diayHora");
			ResultSet resultado1 = consulta.executeQuery();
			resultado1.next();
			int numFilas = resultado1.getInt("numfilas");
			resultado1.close();
			
			resultado = new Object[numFilas][5];
			
			PreparedStatement consulta2 = conexion.prepareStatement("SELECT p.titulo, s.nombreSala, f.diayHora, fp.promocion_descuentoPromo,f.cine_nombreCine " + 
					"FROM pelicula p, peliculasala ps, sala s, funcion f, funcionpromocion fp " + 
					"WHERE p.idPelicula=ps.pelicula_idPelicula AND ps.sala_idSalaCine=s.idSalaCine and s.idSalaCine=f.sala_idSalaCine and f.diayHora=fp.funcion_diayHora;");
			ResultSet resultado2 = consulta2.executeQuery();
			
			int i = 0;
			
			while (resultado2.next()) {
				
				String tituloPeli = resultado2.getString("titulo");
				String nomCine = resultado2.getString("cine_nombreCine");
				String nomSala = resultado2.getString("nombreSala");
				LocalDateTime diayhora = LocalDateTime.parse(resultado2.getString("diayHora"));
				int promo = resultado2.getInt("promocion_descuentoPromo");
				
				
				resultado[i][0] = tituloPeli;
				resultado[i][1] = nomCine;
				resultado[i][2] = nomSala;
				resultado[i][3] = diayhora;
				resultado[i][4] = promo;
				i++;
			}
			conManager.cerrar();
			tablaEntradas.setDataVector(resultado, nombreColumnas);
			return tablaEntradas;
		}catch(SQLException e) {
			System.err.println("Excepcion SQL no controlada");
			e.printStackTrace();
			return null;
		}
	}
	
}
