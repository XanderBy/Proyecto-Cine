package modelo.POJOs;

import java.time.LocalTime;
import java.util.HashMap;

public class Cine implements Cartelera{
	// TODO: Atributos
	private String nombreCine;
	private String direccionCine;
	private int telefonoConsulta;
	public static HashMap<String, Sala> salasCine = new HashMap<String, Sala>();//Convertido en static para acceder desde metodosSala
	public HashMap<LocalTime, Funcion> funcionesSemana = new HashMap<LocalTime, Funcion>(); 
	private int precioBase;
	private int entradasVendidas;
	// TODO:Constructores
	public Cine(String nombreCine, String direccionCine, int telefonoConsulta, int precioBase) {
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

	public int getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(int precioBase) {
		this.precioBase = precioBase;
	}

	public int getEntradasVendidas() {
		return entradasVendidas;
	}

	public void setEntradasVendidas(int entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}

}
