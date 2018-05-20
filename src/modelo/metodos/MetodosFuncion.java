package modelo.metodos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import controlador.ConexionManager;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import modelo.POJOs.Cine;
import modelo.POJOs.Compagnia;
import modelo.POJOs.Funcion;
import modelo.POJOs.Pelicula;
import modelo.POJOs.Promocion;
import modelo.POJOs.Sala;
import static modelo.metodos.MetodosFuncion.Funciones;

public class MetodosCine extends ConexionManager {
    // Crear Cine
    // ---------------------------------------------------------

    public void crearCine(String nombreCine, String direccionCine, int telefonoConsulta, double precioBase) {
        if (nombreCine == null || direccionCine == null || telefonoConsulta == 0 || precioBase == 0) {
            JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
        } else {
            // Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta,
            // precioBase);
            // Compagnia.listaCines.put(nombreCine, cine);
            crearCineBBDD(nombreCine, direccionCine, telefonoConsulta, precioBase);
            eliminarCineArray();
            cogerTodosLosCineBBDD();
            JOptionPane.showMessageDialog(null, "Cine creado con exito");
        }
    }

    // Elimina el cine
    // ----------------------------------------------------------
    public void eliminarCine(String nombreCine) {
        // Compagnia.listaCines.remove(nombreCine);

        eliminarCineBBDD(nombreCine);
        eliminarCineArray();
        cogerTodosLosCineBBDD();
        JOptionPane.showMessageDialog(null, "Cine eliminado");

    }

    // modificar Cine
    // ----------------------------------------------------------
    public void modificarCine(String nombreCineAntiguo, String nombreCine, String direccionCine, int telefonoConsulta,
            double precioBase) {
        if (nombreCineAntiguo == null || nombreCine == null || direccionCine == null || telefonoConsulta == 0
                || precioBase == 0) {
            JOptionPane.showMessageDialog(null, "No has introducido todos los valores");
        } else {
            // Compagnia.listaCines.remove(nombreCineAntiguo);
            // Cine cine = new Cine(nombreCine, direccionCine, telefonoConsulta,
            // precioBase);
            // Compagnia.listaCines.put(nombreCine, cine);
            actualizarCineBBDD(nombreCineAntiguo, nombreCine, direccionCine, telefonoConsulta, precioBase);
            eliminarCineArray();
            cogerTodosLosCineBBDD();
            JOptionPane.showMessageDialog(null, "Cine modificado con exito");
        }
    }

    // ---------------------------------------------------------
    public void eliminarCineArray() {
        Iterator it = Compagnia.listaCines.keySet().iterator();
        while (it.hasNext()) {
            String clave = (String) it.next();
            Compagnia.listaCines.remove(clave);
        }
    }

    // ---------------------------------------------------------
    public void eliminarCineBBDD(String nombreCine) {
        // se arma la consulta
        String q = " DELETE FROM cine WHERE  nombreCine='" + nombreCine + "' ";
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
    public void actualizarCineBBDD(String nombreCineAntiguo, String nombreCine, String direccionCine,
            int telefonoConsulta, double precioBase) {

        // se arma la consulta
        String q = " UPDATE cine " + "SET nombreCine = '" + nombreCine + "', direccionCine = '" + direccionCine
                + "', telefonoConsulta = '" + telefonoConsulta + "', precioBase = '" + precioBase + "'"
                + "WHERE nombreCine= '" + nombreCineAntiguo + " '";
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
    public void crearCineBBDD(String nombreCine, String direccionCine, int telefonoConsulta, double precioBase) {

        // se arma la consulta
        String q = " INSERT INTO cine (nombreCine, direccionCine, telefonoConsulta, precioBase, compania_nombreCompagnia)" + "VALUES ('"
                + nombreCine + "','" + direccionCine + "'," + telefonoConsulta + "," + precioBase + ", 'asd')";
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
    public void cogerTodosLosCineBBDD() {
        // TODO: Aqui se debe coger todos los cines que esten en la bbdd e introducirlos
        // en el hashmap
        System.out.print("prueba al coger todos los cines");
        PreparedStatement pstm = null;
        try {
            pstm = this.getConexion()
                    .prepareStatement("SELECT nombreCine, direccionCine, telefonoConsulta, precioBase FROM cine");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                Cine cine = new Cine(res.getString("nombreCine"), res.getString("direccionCine"),
                        Integer.parseInt(res.getString("telefonoConsulta")),
                        Double.parseDouble(res.getString("precioBase")));
                Compagnia.listaCines.put(res.getString("nombreCine"), cine);
                System.out.print("prueba al coger todos los cines");
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.print("prueba al coger todos los cines1  "+ Compagnia.listaCines.size() + "       ");
    }

    // ---------------------------------------------------------
    public DefaultTableModel cogerCineBBDDNombre() {
        System.out.println("prueba");
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        PreparedStatement pstm = null;
        String[] columNames = {"nombre Cine"};
        // obtenemos la cantidad de registros existentes en la tabla y se almacena en la
        // variable "registros"
        // para formar la matriz de datos
        try {
            System.out.println("pruebass");
            pstm = getConexion().prepareStatement("SELECT count(*) as total FROM cine");
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
            pstm = this.getConexion().prepareStatement("SELECT nombreCine FROM cine");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("nombreCine");
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

    public DefaultTableModel cogerCineBBDDTodo() {
        System.out.println("pruebaaaaaaaa");
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        PreparedStatement pstm = null;
        String[] columNames = {"nombre Cine", "direcion cine", "telefono", "precio Base"};
        // obtenemos la cantidad de registros existentes en la tabla y se almacena en la
        // variable "registros"
        // para formar la matriz de datos
        try {
            System.out.println("pruebass");
            pstm = getConexion().prepareStatement("SELECT count(*) as total FROM cine");
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
            pstm = this.getConexion().prepareStatement("SELECT nombreCine, direccionCine, telefonoConsulta, precioBase, compania_nombreCompagnia FROM cine");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("nombreCine");
                data[i][1] = res.getString("direccionCine");
                data[i][2] = res.getString("telefonoConsulta");
                data[i][3] = res.getString("precioBase");
                data[i][4] = res.getString("compania_nombreCompagnia");
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

}
