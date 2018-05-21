package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;

import controlador.ConexionManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;
import modelo.POJOs.Funcion;
import modelo.POJOs.Pelicula;
import modelo.POJOs.Promocion;
import modelo.POJOs.Sala;

public class MetodosFuncion extends ConexionManager {
    
    public static HashMap<LocalDateTime, Funcion> Funciones = new HashMap<LocalDateTime, Funcion>();

    /*
	 * Falta-----
	 * 
	 * falta coger las funcionesSemana linea 81 falta implementar coger funciones
	 * 
	 * Cambiar los constructores de objeto a strings
     */
    // ---------------------------------------------------------
    public void crearFuncion(LocalDateTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion, Cine cine_nombreCine,
            Promocion promocionFuncion) {
        if (diaYHora == null || salaFuncion == null || peliculaFuncion == null || promocionFuncion == null) {
            JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
        } else {
            // Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion,
            // promocionFuncion);
            // Funciones.put(diaYHora, funcion);

            crearFuncionBBDD(diaYHora, salaFuncion, peliculaFuncion, cine_nombreCine, promocionFuncion);
            eliminarFuncionesArray();
            cogerTodasLasFuncionesBBDD();
            
            JOptionPane.showMessageDialog(null, "Funcion Aniadida");
        }
    }

    // ---------------------------------------------------------
    public void eliminarFuncion(LocalDateTime diaYHora) {
        // Funciones.remove(diaYHora);

        eliminarFuncionBBDD(diaYHora);
        // eliminarFuncionesArray();// TODO:Aqui a lo mejor de error
        // cogerTodasLasFuncionesBBDD();
        JOptionPane.showMessageDialog(null, "Funcion Eliminada");
    }
 // ---------------------------------------------------------
    public void eliminarFuncionCine(LocalDateTime diaYHora) {
        // Funciones.remove(diaYHora);

        eliminarFuncionCineBBDD(diaYHora);
        // eliminarFuncionesArray();// TODO:Aqui a lo mejor de error
        // cogerTodasLasFuncionesBBDD();
        JOptionPane.showMessageDialog(null, "Funcion Eliminada");
    }
    // TODO: TENGO QUE COMPROBAR ESTO ME QUEDA HACER METODO MODIFICAR
    // FUNCIONPROMOCION
    // ---------------------------------------------------------
    public void modificarFuncion(LocalDateTime diaYHoraAntiguo, LocalDateTime diaYHora, Sala salaFuncion,
            Pelicula peliculaFuncion, Promocion promocionFuncion, Cine cine_nombreCine) {
        if (diaYHoraAntiguo == null || diaYHora == null ) {
            JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
        } else {
            // Funciones.remove(diaYHoraAntiguo);
            // Funcion funcion = new Funcion(diaYHora, salaFuncion, peliculaFuncion,
            // promocionFuncion);
            // Funciones.put(diaYHora, funcion);
            actualizarFuncionBBDD(diaYHoraAntiguo, diaYHora, salaFuncion, peliculaFuncion, cine_nombreCine);
            // eliminarFuncionesArray();
            // cogerTodasLasFuncionesBBDD();
            JOptionPane.showMessageDialog(null, "Funcion modificada");
        }
    }
    // ---------------------------------------------------------
    public void eliminarFuncionCineBBDD(LocalDateTime diaYHora) {
        // se arma la consulta
        String q = " UPDATE funcion SET cine_nombreCine= 'null' WHERE diayHora='"+ diaYHora + "'";
        // se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    // ---------------------------------------------------------
    public void eliminarFuncionBBDD(LocalDateTime diaYHora) {
        // se arma la consulta
       // String q = " DELETE FROM funcion  WHERE  diaYHora='" + diaYHora + "' ";
    	String q= "DELETE FROM funcion f INNER JOIN funcionPromocion fp ON f.diayHora= fp.funcion_diayHora WHERE diayHora='"+ diaYHora+"'";
        // se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // ---------------------------------------------------------(tiene la tabla
    // funcionpromocion tambien)
    public void crearFuncionBBDD(LocalDateTime diaYHora, Sala salaFuncion, Pelicula peliculaFuncion,
            Cine cine_nombreCine, Promocion promocionFuncion) {

        // se arma la consulta
        String q = " INSERT INTO funcion (diayHora, sala_idSalaCine, peliculaFuncion, cine_nombreCine)" + "VALUES ("
                + diaYHora + "," + salaFuncion.getIdSalaCine() + "," + peliculaFuncion.getIdPelicula() + ","
                + cine_nombreCine.getNombreCine() + ")";
        // se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        // se arma la consulta
        String qq = " INSERT INTO funcionPromocion (funcion_diayHora, promocion_descuentoPromo)" + "VALUES (" + diaYHora
                + "," + promocionFuncion.getDescuentoPromo() + ")";
        // se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(qq);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // ---------------------------------------------------------
    public void actualizarFuncionBBDD(LocalDateTime diaYHoraAntiguo, LocalDateTime diaYHora, Sala salaFuncion,
            Pelicula peliculaFuncion, Cine cine_nombre) {
        // se arma la consulta
        String q = " UPDATE funcion SET diayHora = " + diaYHora + ", PeliculaFuncion = "
                + peliculaFuncion.getIdPelicula() + ", sala_idSalaCine = '" + salaFuncion.getIdSalaCine() + "'"
                + "WHERE diayHora= " + diaYHoraAntiguo;
        // se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // ---------------------------------------------------------
    public void actualizarFuncionCineBBDD(String diaYHoraAntiguo, Cine cine_nombre) {
        // se arma la consulta
        String q = " UPDATE funcion " + "SET cine_nombreCine = '" + cine_nombre.getNombreCine() + "'"
                + " WHERE diayHora= '" + diaYHoraAntiguo + " '";
        
        System.out.println("dawwwwwwwwwwww" + Compagnia.listaCines.get(cine_nombre.getNombreCine()).funcionesSemana.size());
        // se ejecuta la consulta
        cogerTodasLasFuncionesBBDD();
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // ---------------------------------------------------------
    public void eliminarFuncionesArray() {
        Iterator it = Funciones.keySet().iterator();
        while (it.hasNext()) {
            String clave = (String) it.next();
            Funciones.remove(clave);
        }
    }
    
    public void eliminarFuncionesSemanasArray(String nombreCine) {
        Iterator it = Compagnia.listaCines.get(nombreCine).funcionesSemana.keySet().iterator();
        while (it.hasNext()) {
            String clave = (String) it.next();
            Compagnia.listaCines.get(nombreCine).funcionesSemana.remove(clave);
        }
    }
    // ---------------------------------------------------------

    public Sala encuentraKeyStringHashMapSala(String key) {
        
        Sala resultado = null;
        
        try {
            Iterator it = MetodosSala.salas.keySet().iterator();
            while (it.hasNext()) {
                String clave = (String) it.next();
                if (clave.equals(key)) {
                    resultado = MetodosSala.salas.get(key);
                }
            }
        } catch (Exception e) {
            System.out.println("Excepcion no controlada");
            e.printStackTrace();
        }
        return resultado;
    }
    // ---------------------------------------------------------

    public Pelicula encuentraKeyStringHashMapPelicula(Integer key) {
        
        Pelicula resultado = null;
        
        try {
            Iterator it = MetodosPelicula.peliculas.keySet().iterator();
            while (it.hasNext()) {
                Integer clave = (Integer) it.next();
                if (clave == key) {
                    resultado = MetodosPelicula.peliculas.get(key);
                }
            }
        } catch (Exception e) {
            System.out.println("Excepcion no controlada");
            e.printStackTrace();
        }
        return resultado;
    }
    // ---------------------------------------------------------

    public Promocion encuentraKeyStringHashMapPromocion(Integer key) {
        
        Promocion resultado = null;
        
        try {
            Iterator it = MetodosPromocion.mapPromocionesCreadas.keySet().iterator();
            while (it.hasNext()) {
                int clave = (Integer) it.next();
                if (clave == key) {
                    resultado = MetodosPromocion.mapPromocionesCreadas.get(key);
                }
            }
        } catch (Exception e) {
            System.out.println("Excepcion no controlada");
            e.printStackTrace();
        }
        return resultado;
    }
    // ---------------------------------------------------------

    public void cogerTodasLasFuncionesBBDD() {
        PreparedStatement pstm = null;
        try {
            pstm = this.getConexion().prepareStatement(
                    "SELECT diayHora, sala_idSalaCine, peliculaFuncion, cine_nombreCine, fp.promocion_descuentoPromo FROM funcion f INNER JOIN funcionPromocion fp ON f.diayHora= fp.funcion_diayHora");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            eliminarFuncionesSemanasArray(res.getString("cine_nombreCine"));
            while (res.next()) {
                LocalDateTime tiempo = LocalDateTime.parse(res.getString("diaYHora"));
                Sala sala = encuentraKeyStringHashMapSala(res.getString("sala_idSalaCine"));
                Pelicula peliculaPromocion1 = encuentraKeyStringHashMapPelicula(
                        Integer.parseInt(res.getString("peliculaFuncion")));
                Promocion promocionFuncion1 = encuentraKeyStringHashMapPromocion(
                        Integer.parseInt(res.getString("fp.promocion_descuentoPromo")));
                // localizar la sala pelicula etc.. buscando con un iterator en los hashmap
                Funcion funcion = new Funcion(tiempo, sala, peliculaPromocion1, promocionFuncion1);
                Funciones.put(tiempo, funcion);
                Compagnia.listaCines.get(res.getString("cine_nombreCine")).funcionesSemana.put(tiempo, funcion);
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
    }

    // ---------------------------------------------------------
    public DefaultTableModel cogerFuncionBBDDNombre() {
        System.out.println("prueba");
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        PreparedStatement pstm = null;
        String[] columNames = {"Dia y Hora"};
        // obtenemos la cantidad de registros existentes en la tabla y se almacena en la
        // variable "registros"
        // para formar la matriz de datos
        try {
            // TODO: No se si se llama asi la tabla
            pstm = getConexion().prepareStatement("SELECT count(*) as total FROM funcion");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][1];
        try {
            // realizamos la consulta sql y llenamos los datos en la matriz "Object[][]
            // data"
            pstm = this.getConexion().prepareStatement("SELECT diaYHora FROM Funcion");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("diaYHora");
                i++;
            }
            res.close();
            // se anade la matriz de datos en el DefaultTableModel
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
        
    }
    // ---------------------------------------------------------

    public DefaultTableModel cogerFuncionBBDDCine(Cine nombreCine) {
        System.out.println("prueba");
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        PreparedStatement pstm = null;
        String[] columNames = {"diayHora", "sala_idSalaCine", "peliculaFuncion", "cine_nombreCine", "promocion_descuento", "diayHora2"};
        // obtenemos la cantidad de registros existentes en la tabla y se almacena en la
        // variable "registros"
        // para formar la matriz de datos
        try {
            System.out.println("pruebass");
            System.out.print(nombreCine.getNombreCine());
            try {
                pstm = getConexion().prepareStatement("SELECT COUNT(*) AS total FROM funcion f INNER JOIN funcionpromocion fp ON f.diayHora= fp.funcion_diayHora WHERE f.cine_nombreCine = '" + nombreCine.getNombreCine() + "'");
            } catch (NullPointerException e) {
                System.out.print("error creooooo");
                return null;
            }
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][6];
        try {
            // realizamos la consulta sql y llenamos los datos en la matriz "Object[][]
            // data"
            pstm = this.getConexion().prepareStatement("SELECT f.diayHora, sala_idSalaCine, peliculaFuncion, cine_nombreCine, promocion_descuentoPromo, fp.funcion_diayHora FROM funcion f INNER JOIN funcionPromocion fp ON f.diayHora= fp.funcion_diayHora");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("f.diayHora");
                data[i][1] = res.getString("fp.funcion_diayHora");
                data[i][2] = res.getString("sala_idSalaCine");
                data[i][3] = res.getString("peliculaFuncion");
                data[i][4] = res.getString("cine_nombreCine");
                data[i][5] = res.getString("promocion_descuentoPromo");
                i++;
            }
            res.close();
            // se anade la matriz de datos en el DefaultTableModel
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
        
    }
    // ---------------------------------------------------------

    public DefaultTableModel cogerFuncionBBDDTodo() {
        System.out.println("prueba1");
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        PreparedStatement pstm = null;
        String[] columNames = {"diayHora", "sala_idSalaCine", "peliculaFuncion", "cine_nombreCine", "promocion_descuento"};
        // obtenemos la cantidad de registros existentes en la tabla y se almacena en la
        // variable "registros"
        // para formar la matriz de datos
        try {
            System.out.println("pruebass1");
            pstm = getConexion().prepareStatement("SELECT COUNT(*) AS total FROM funcion f INNER JOIN funcionPromocion fp ON f.diayHora= fp.funcion_diayHora");
            System.out.println("pruebas2");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][5];
        try {
            // realizamos la consulta sql y llenamos los datos en la matriz "Object[][]
            // data"
            pstm = this.getConexion().prepareStatement("SELECT diayHora, sala_idSalaCine, peliculaFuncion, cine_nombreCine, promocion_descuentoPromo FROM funcion f INNER JOIN funcionPromocion fp ON f.diayHora= fp.funcion_diayHora");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            
            while (res.next()) {
                data[i][0] = res.getString("diayHora");
                data[i][1] = res.getString("sala_idSalaCine");
                data[i][2] = res.getString("peliculaFuncion");
                data[i][3] = res.getString("cine_nombreCine");
                data[i][4] = res.getString("promocion_descuentoPromo");
                System.out.println(1);
                i++;
            }
            System.out.println(i);
            res.close();
            // se anade la matriz de datos en el DefaultTableModel
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("pruebas3");
        return tablemodel;
        
    }
}
