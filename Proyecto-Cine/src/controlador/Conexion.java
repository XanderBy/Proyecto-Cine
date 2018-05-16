package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	//Lo he cambiado de private a public
    public static Connection conex = null;
    
    //CREAR CONEXION
    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (conex == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddCine", "root", "");//Puerto, Usuario, Contrasenia
                //(*) Obtener puerto: SHOW VARIABLES WHERE variable_name IN('hostname', 'port')
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return conex;
    }

    public static void cerrar() throws SQLException {
        if (conex != null) {
            conex.close();
        }
    }
}
