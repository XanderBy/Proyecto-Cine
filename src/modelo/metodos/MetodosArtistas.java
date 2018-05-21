package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import controlador.ConexionManager;
import javax.swing.JOptionPane;
import modelo.POJOs.Artista;
import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;
import modelo.POJOs.Pais;
import modelo.POJOs.Pelicula;

public class MetodosArtistas extends ConexionManager {
	public static HashMap<String, Artista> Artistas = new HashMap<String, Artista>();

	// Creas Artista
	//------------------------------------------------------
	public void CrearArtista(String nombreCompleto, Pais nacionalidad) {
            if(nombreCompleto==null || nacionalidad==null){
                JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
            }else{
		Artista artista = new Artista(nombreCompleto, nacionalidad);
		Artistas.put(nombreCompleto, artista);
                JOptionPane.showMessageDialog(null, "Artista creador con exito");
            }
	}
	//------------------------------------------------------
	public void eliminarArtista(String nombreCompleto) {
		
	}
	//------------------------------------------------------
	// Anade el artista al Array director
	public void AnadirADirector(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaActor.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
                JOptionPane.showMessageDialog(null, "Artista Añadido ha director");
	}
	//------------------------------------------------------
	// Anade el artista al Array actor
	public void AnadirAActor(String nombreCompleto, Pelicula pelicula) {
		Artistas.get(nombreCompleto).PeliculasParticipaDirector.put(nombreCompleto, pelicula);
		masUnoCantidadPeliculas(nombreCompleto);
                JOptionPane.showMessageDialog(null, "Artista Añadido ha Actor");
	}
	//------------------------------------------------------
	// Este metodo anade +1 a la cantidad de peliculas del Artista
	public void masUnoCantidadPeliculas(String nombreCompleto) {
		if (Artistas.get(nombreCompleto).PeliculasParticipaDirector.containsKey(nombreCompleto)
				&& Artistas.get(nombreCompleto).PeliculasParticipaActor.containsKey(nombreCompleto)) {
			System.out.println("No se suma + 1 en cantidad pelicula");

		} else if (Artistas.get(nombreCompleto).PeliculasParticipaDirector.containsKey(nombreCompleto)) {
			Artistas.get(nombreCompleto)
					.setCantidadPeliculaParticipa(Artistas.get(nombreCompleto).getCantidadPeliculaParticipa() + 1);
			System.out.println("Se suma + 1 en cantidad pelicula");

		} else if (Artistas.get(nombreCompleto).PeliculasParticipaActor.containsKey(nombreCompleto)) {
			Artistas.get(nombreCompleto)
					.setCantidadPeliculaParticipa(Artistas.get(nombreCompleto).getCantidadPeliculaParticipa() + 1);
			System.out.println("Se suma + 1 en cantidad pelicula");

		}
	}
	//------------------------------------------------------
	public void eliminarArtistaBBDD() {

	}
	//------------------------------------------------------
	public void insertarArtistaBBDD(String nombre, String nacionalidad, int cantidadPeliculas) {
		 // se arma la consulta
        String q = " INSERT INTO cine (nombreCompleto, nacionalidad, cantidadPeliculas)" + "VALUES ('"
                + nombre + "','" + nacionalidad + "'," + cantidadPeliculas +")";
        // se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

	}
	//------------------------------------------------------
	public void eliminarArtistasArray() {
		Iterator it = Artistas.keySet().iterator();
        while (it.hasNext()) {
            String clave = (String) it.next();
            Artistas.remove(clave);
        }
	}
	//------------------------------------------------------
	public void devolverArtistasArray() {
		// se arma la consulta
        String q = "SELECT nombreCompleto, nacionalidad, cantidadPeliculas FROM artista ";
        // se ejecuta la consulta
        try {
        	Pais enumerador = null;
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            eliminarArtistasArray();
            int i = 0;
            while (res.next()) {
            	enumerador=enumerador.valueOf(res.getString("nacionalidad"));
                 
                Artista artista = new Artista(res.getString("nombreCompleto"), enumerador);
                artista.setCantidadPeliculaParticipa(Integer.parseInt(res.getString("cantidadPeliculas")));
                Artistas.put(res.getString("nombreCompleto"), artista);
                i++;
            }
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
	}
	//------------------------------------------------------
	public void actualizarArtistaBBDD(String nombreCompeltoAntiguo,String nombreCompleto, String nacionalidad) {
		String q = " UPDATE artista " + "SET nombreCompleto = '" + nombreCompleto + "','"+ nacionalidad
                + "' WHERE diayHora= '" + nombreCompeltoAntiguo + " '";
        
       
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
	}
	//------------------------------------------------------
	public void cogerArtistaBBDD() {

	}
}
