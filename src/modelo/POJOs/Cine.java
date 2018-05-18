package modelo.POJOs;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import modelo.metodos.MetodosFuncion;

public class Cine implements Cartelera{
	// TODO: Atributos
	private String nombreCine;
	private String direccionCine;
	private int telefonoConsulta;
	public static HashMap<String, Sala> salasCine = new HashMap<String, Sala>();//Convertido en static para acceder desde metodosSala
	public HashMap<LocalDateTime, Funcion> funcionesSemana = new HashMap<LocalDateTime, Funcion>(); 
	private double precioBase;
	private int entradasVendidas;
	// TODO:Constructores
	public Cine(String nombreCine, String direccionCine, int telefonoConsulta, double precioBase) {
		super();
		this.nombreCine = nombreCine;
		this.direccionCine = direccionCine;
		this.telefonoConsulta = telefonoConsulta;
		this.precioBase = precioBase;
	}

	// TODO: Metodos Get/Set
	public String getNombreCine() {
		return nombreCine;
	}

	public void setNombreCine(String nombreCine) {
		this.nombreCine = nombreCine;
	}

	public String getDireccionCine() {
		return direccionCine;
	}

	public void setDireccionCine(String direccionCine) {
		this.direccionCine = direccionCine;
	}

	public int getTelefonoConsulta() {
		return telefonoConsulta;
	} 

	public void setTelefonoConsulta(int telefonoConsulta) {
		this.telefonoConsulta = telefonoConsulta;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}

	@Override
	public void nuevaSemana() {
		Calendar c2 = new GregorianCalendar();
		if(c2.get(Calendar.DAY_OF_WEEK)==1) {
		Iterator it = funcionesSemana.keySet().iterator();
		while(it.hasNext()){
		Integer key = (Integer) it.next();
		funcionesSemana.remove(key);
		}
		}else {
			System.out.println("Aun no es lunes");
		}
		
	}

}
