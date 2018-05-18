package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection conex = null;
    
    //CREAR CONEXION
    public Connection obtener() throws SQLException, ClassNotFoundException {
        if (conex == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Modificar nombre BBDD para pruebas locales
                conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebaproyecto", "root", "");//Puerto, Usuario, Contrasenia
                //(*) Obtener puerto: SHOW VARIABLES WHERE variable_name IN('hostname', 'port')
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return conex;
    }

    public void cerrar() throws SQLException {
        if (conex != null) {
            conex.close();
        }
    }
    
    public Connection getConexion(){
        return this.conex;
    }
}
