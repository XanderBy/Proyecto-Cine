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

public class ControladorCine implements ActionListener, MouseListener {

    // Instanciamos vista PantallaAdministrador
    public PantallaAdministrador pantallaAdministrador = new PantallaAdministrador();
    // Instanciamos modelos
    public MetodosFuncion metodosFuncion = new MetodosFuncion();
    public MetodosCine metodosCine = new MetodosCine();
    public MetodosPromocion metodosPromocion = new MetodosPromocion();
    public MetodosSala metodosSala = new MetodosSala();
    public MetodosPelicula metodosPelicula = new MetodosPelicula();
    // Declaracion de variables
    private String now = "2016-11-09 10:30";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    private LocalDateTime formatDateTime;
    private String nombreCineA;
    private String oldPromoDiscount;//Fijate Antonio
    // Declaramos en un enum las acciones relacionadas con el Administrador

    public enum accionesAdministrador {//Fijate Antonio
        ELIMINAR_CINE, ANIADIR_CINE, MODIFICAR_CINE, ELEGIR_CINE, RECARGAR_TABLA, ELIMINAR_FUNCION, ANIADIR_FUNCION, MODIFICAR_FUNCION, ELEGIR_FUNCION, // Este
        // ultimo
        // dudo
        CREAR_PROMOCION, MODIFICAR_PROMOCION, ELIMINAR_PROMOCION, ANIADIR_SALA, MODIFICAR_SALA
    }

    // CONSTRUCTOR DE CLASE
    public ControladorCine(PantallaAdministrador pantallaAdministrador) {
        super();
        this.pantallaAdministrador = pantallaAdministrador;
    }

    // INICIAMOS
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

        // Declaramos las acciones y aniadimos las escuchas al evento producido por el
        // componente
        // Funcion
        this.pantallaAdministrador.jTable2.addMouseListener(this);
        this.pantallaAdministrador.jTable2.setModel(new DefaultTableModel());

        this.pantallaAdministrador.jTable8.addMouseListener(this);
        this.pantallaAdministrador.jTable8.setModel(new DefaultTableModel());

        this.pantallaAdministrador.jTable11.addMouseListener(this);
        this.pantallaAdministrador.jTable11.setModel(new DefaultTableModel());
        // Cine
        this.pantallaAdministrador.jTable7.addMouseListener(this);
        this.pantallaAdministrador.jTable7.setModel(new DefaultTableModel());

        this.pantallaAdministrador.jTable9.addMouseListener(this);
        this.pantallaAdministrador.jTable9.setModel(new DefaultTableModel());

        // Recargar tabla cine
        this.pantallaAdministrador.jButton7.setActionCommand("RECARGAR_TABLA");
        this.pantallaAdministrador.jButton7.addActionListener(this);

        // Eliminar cine
        this.pantallaAdministrador.jButton4.setActionCommand("ELIMINAR_CINE");
        this.pantallaAdministrador.jButton4.addActionListener(this);

        // Aniadir Cine
        this.pantallaAdministrador.jButton3.setActionCommand("ANIADIR_CINE");
        this.pantallaAdministrador.jButton3.addActionListener(this);

        // Elegir Cine
        this.pantallaAdministrador.jButton2.setActionCommand("ELEGIR_CINE");
        this.pantallaAdministrador.jButton2.addActionListener(this);

        // Modificar Cine
        this.pantallaAdministrador.jButton6.setActionCommand("MODIFICAR_CINE");
        this.pantallaAdministrador.jButton6.addActionListener(this);
        //ELIMINAR FUNCION
         this.pantallaAdministrador.jButton22.setActionCommand("ELIMINAR_FUNCION");
        this.pantallaAdministrador.jButton22.addActionListener(this);
        // Aniadir Funcion
        this.pantallaAdministrador.jButton30.setActionCommand("ANIADIR_FUNCION");
        this.pantallaAdministrador.jButton30.addActionListener(this);

        // Crear promocion
        pantallaAdministrador.botonAniadirPromocion.setActionCommand("CREAR_PROMOCION");
        pantallaAdministrador.botonAniadirPromocion.addActionListener(this);
        pantallaAdministrador.botonAniadirPromocion.addMouseListener(this);

        // Modificar promocion
        pantallaAdministrador.tablaModificarPromocion.addMouseListener(this);//Fijate Antonio
        pantallaAdministrador.tablaModificarPromocion.setModel(metodosPromocion.generarTablaPromociones());
        pantallaAdministrador.botonModificarPromocion.setActionCommand("MODIFICAR_PROMOCION");
        pantallaAdministrador.botonModificarPromocion.addActionListener(this);
        pantallaAdministrador.botonModificarPromocion.addMouseListener(this);

        // Eliminar promocion
        pantallaAdministrador.tablaEliminarPromocion.addMouseListener(this);
        pantallaAdministrador.tablaEliminarPromocion.setModel(metodosPromocion.generarTablaPromociones());
        pantallaAdministrador.botonEliminarPromocion.setActionCommand("ELIMINAR_PROMOCION");
        pantallaAdministrador.botonEliminarPromocion.addActionListener(this);
        pantallaAdministrador.botonEliminarPromocion.addMouseListener(this);

        // TABLAS ALE
        //
        pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDDTodo());
        pantallaAdministrador.jTable9.setModel(metodosCine.cogerCineBBDDTodo());
         //coger las funciones del cine no todas
        pantallaAdministrador.jTable6.setModel(metodosFuncion.cogerFuncionBBDDTodo());
        pantallaAdministrador.jTable8.setModel(metodosFuncion.cogerFuncionBBDDTodo());
        pantallaAdministrador.jTable11.setModel(metodosFuncion.cogerFuncionBBDDNombre());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (accionesAdministrador.valueOf(e.getActionCommand())) {
            case ANIADIR_CINE:
                int numero = 0;
                double precioBase = 0;
                String nombreCine = pantallaAdministrador.jTextField1.getText();
                System.out.println(nombreCine);
                String direccion = pantallaAdministrador.jTextField2.getText();
                if (pantallaAdministrador.jTextField3.getText().length() == 0) {
                    numero = 0;
                } else {
                    numero = Integer.parseInt(pantallaAdministrador.jTextField3.getText());
                }
                if (pantallaAdministrador.jTextField4.getText().length() == 0) {
                    precioBase = 0;
                } else {
                    precioBase = Double.parseDouble(pantallaAdministrador.jTextField4.getText());
                }

                metodosCine.crearCine(nombreCine, direccion, numero, precioBase);
                pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDDTodo());
                pantallaAdministrador.jTable9.setModel(metodosCine.cogerCineBBDDTodo());
                break;
            case ELIMINAR_CINE:
                metodosCine.eliminarCine(nombreCineA);
                pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDDTodo());
                pantallaAdministrador.jTable9.setModel(metodosCine.cogerCineBBDDTodo());
                break;
            case ELEGIR_FUNCION:
                 
                break;
            case MODIFICAR_CINE:

                metodosCine.actualizarCineBBDD(pantallaAdministrador.jLabel91.getText(),
                        pantallaAdministrador.jTextField11.getText(), pantallaAdministrador.jTextField12.getText(),
                        Integer.parseInt(pantallaAdministrador.jTextField5.getText()),
                        Double.parseDouble(pantallaAdministrador.jTextField8.getText()));
                pantallaAdministrador.jTable7.setModel(metodosCine.cogerCineBBDDTodo());
                pantallaAdministrador.jTable9.setModel(metodosCine.cogerCineBBDDTodo());
                break;
            case ANIADIR_FUNCION:
                // "2016-11-09 10:30" tengo que poner este formato
                
                now = pantallaAdministrador.jTextField19.getText();

                

                formatDateTime = LocalDateTime.parse(now, formatter);

                // pantallaAdministrador.jTextField20.getText() Sala
                // pantallaAdministrador.jTextField21.getText() Pelicula
                // pantallaAdministrador.jTextField22.getText() Promocion
                metodosFuncion.crearFuncion(formatDateTime,
                        MetodosSala.salas.get(pantallaAdministrador.jTextField20.getText()),
                        MetodosPelicula.peliculas.get(Integer.parseInt(pantallaAdministrador.jTextField21.getText())), null,
                        MetodosPromocion.mapPromocionesCreadas
                                .get(Integer.parseInt(pantallaAdministrador.jTextField22.getText())));

                break;
            case ELIMINAR_FUNCION:
                now = pantallaAdministrador.jTextField27.getText();

                formatDateTime = LocalDateTime.parse(now, formatter);
                System.out.print("pruebaaaaaaaaaa");
                metodosFuncion.eliminarFuncion(formatDateTime );
                break;
            case CREAR_PROMOCION:
                try {
                    String descripcionPromo = pantallaAdministrador.textoDescripcionPromocionAniadir.getText();
                    int descuentoPromo = Integer.parseInt(pantallaAdministrador.textoDescuentoPromocionAniadir.getText());
                    try {
                        metodosPromocion.crearPromocion(descuentoPromo, descripcionPromo);
                    } catch (SQLException e1) {
                        System.err.println("Error SQL");
                        e1.printStackTrace();
                    }
                    System.out.println("Ok controlador");
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Debe introducir valores correctos");
                }
                try {
                    pantallaAdministrador.textoDescripcionPromocionAniadir.setText("");
                    pantallaAdministrador.textoDescuentoPromocionAniadir.setText("");
                } catch (Exception e1) {
                    System.err.println("Excepcion no controlada");
                    e1.printStackTrace();
                }
                break;
            case MODIFICAR_PROMOCION://Fijate Antonio
                try {
                    String descripcionPromo = pantallaAdministrador.textoDescripcionPromocionModificar.getText();
                    int descuentoPromo = Integer.parseInt(pantallaAdministrador.textoDescuentoPromocionModificar.getText());
                    int antiguoDescuentoPromo = Integer.parseInt(oldPromoDiscount);
                    if (descripcionPromo != null && descuentoPromo != 0 && antiguoDescuentoPromo != 0) {
                        metodosPromocion.modificarPromocion(antiguoDescuentoPromo, descripcionPromo, descuentoPromo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe introducir datos validos");
                    }
                } catch (NumberFormatException | SQLException e1) {
                    System.err.println("Excepcion SQL no controlada");
                    System.err.println("Excepcion NumberFormatException no controlada");
                    e1.printStackTrace();
                }
                break;
            case ELIMINAR_PROMOCION:
                try {
                    int promoDiscount = Integer.parseInt(oldPromoDiscount);
                    if (promoDiscount != 0) {
                        metodosPromocion.eliminarPromocion(promoDiscount);
                    } else {
                        JOptionPane.showMessageDialog(null, "La promocion ya no existe");
                    }
                } catch (NumberFormatException | SQLException e1) {
                    System.err.println("Excepcion SQL no controlada");
                    System.err.println("Excepcion NumberFormatException no controlada en eliminar promocion");
                    e1.printStackTrace();
                }
                break;

            default:
                System.out.println("Entra en default");
                break;
        }

    }

    // METODOS
    private void presionarJTable7(java.awt.event.MouseEvent e) {

        if (e.getButton() == 1)// boton izquierdo
        {
            int fila = this.pantallaAdministrador.jTable7.rowAtPoint(e.getPoint());
            if (fila > -1) {
                nombreCineA = String.valueOf(this.pantallaAdministrador.jTable7.getValueAt(fila, 0));

            }
        }
    }

    private void presionarJTable11(java.awt.event.MouseEvent e) {

        if (e.getButton() == 1)// boton izquierdo
        {
            int fila = this.pantallaAdministrador.jTable11.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.pantallaAdministrador.jTextField27.setText(String.valueOf(this.pantallaAdministrador.jTable11.getValueAt(fila, 0)));

            }
        }
    }

    private void presionarJTable9(java.awt.event.MouseEvent e) {
        if (e.getButton() == 1)// boton izquierdo
        {
            int fila = this.pantallaAdministrador.jTable9.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.pantallaAdministrador.jLabel91
                        .setText(String.valueOf(this.pantallaAdministrador.jTable9.getValueAt(fila, 0)));
                try{
                    
                    metodosCine.cogerTodosLosCineBBDD();
                //El error esta aqui 
                pantallaAdministrador.jTable2.setModel(metodosFuncion.cogerFuncionBBDDCine(Compagnia.listaCines.get(String.valueOf(this.pantallaAdministrador.jTable2.getValueAt(fila, 0)))));
                System.out.print("error al coger la tabla");
                }catch(IllegalArgumentException ew){
                    System.out.print("no tiene funciones");
                }
                this.pantallaAdministrador.jTextField11
                        .setText(String.valueOf(this.pantallaAdministrador.jTable9.getValueAt(fila, 0)));
                this.pantallaAdministrador.jTextField12
                        .setText(String.valueOf(this.pantallaAdministrador.jTable9.getValueAt(fila, 1)));
                this.pantallaAdministrador.jTextField5
                        .setText(String.valueOf(this.pantallaAdministrador.jTable9.getValueAt(fila, 2)));
                this.pantallaAdministrador.jTextField8
                        .setText(String.valueOf(this.pantallaAdministrador.jTable9.getValueAt(fila, 3)));
                metodosFuncion.cogerFuncionBBDDCine(Compagnia.listaCines
                        .get(String.valueOf(this.pantallaAdministrador.jTable9.getValueAt(fila, 0))));
            }
        }
    }

    private void presionarTablaModificarPromocion(java.awt.event.MouseEvent e) {//Fijate Antonio
        if (e.getButton() == 1) {
            int fila = this.pantallaAdministrador.tablaModificarPromocion.rowAtPoint(e.getPoint());
            if (fila > -1) {
                oldPromoDiscount = String
                        .valueOf(this.pantallaAdministrador.tablaModificarPromocion.getValueAt(fila, 1));
                this.pantallaAdministrador.textoDescuentoPromocionModificar.setText(
                        String.valueOf(this.pantallaAdministrador.tablaModificarPromocion.getValueAt(fila, 1)));
                this.pantallaAdministrador.textoDescripcionPromocionModificar.setText(
                        String.valueOf(this.pantallaAdministrador.tablaModificarPromocion.getValueAt(fila, 0)));
                // Recarga la tabla cada vez que se hace click sobre la misma
                pantallaAdministrador.tablaModificarPromocion.setModel(metodosPromocion.generarTablaPromociones());
            }
        }
    }

    private void presionarTablaEliminarPromocion(java.awt.event.MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.pantallaAdministrador.tablaEliminarPromocion.rowAtPoint(e.getPoint());
            if (fila > -1) {
                oldPromoDiscount = String
                        .valueOf(this.pantallaAdministrador.tablaEliminarPromocion.getValueAt(fila, 1));
                // Recarga la tabla cada vez que se hace click sobre la misma
                pantallaAdministrador.tablaEliminarPromocion.setModel(metodosPromocion.generarTablaPromociones());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        presionarJTable9(e);
        presionarJTable7(e);
        presionarJTable11(e);
        presionarTablaModificarPromocion(e);//Fijate Antonio
        presionarTablaEliminarPromocion(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
