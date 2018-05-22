package modelo.POJOs;

public class Entrada {
	
	//ATRIBUTOS
	private int idEntrada;
	private String idSala;
	private double precioEntrada;
	private int idPelicula;
	private String idCine;
	private String idCompania;
	private String idUsuario;
	private static int contador=1;
	private String clavePrimaria;
	
	//CONSTRUCTOR VACIO
	public Entrada() {
		
	}
	
	//CONSTRUCTOR DEFINIDO
	public Entrada(int idTicket,String idHall, double ticketPrice, int idMovie, String idCin, String idUser) {
		idEntrada=idTicket;
		idSala=idHall;
		precioEntrada=ticketPrice;
		idPelicula=idMovie;
		idCine=idCin;
		idCompania=idCin;
		idUsuario=idUser;
		clavePrimaria=idHall.concat(idCine).concat(idCompania).concat(idUsuario);
	}
	
	//ACCEDENTES Y MUTADORES
	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public String getIdSala() {
		return idSala;
	}

	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

	public double getPrecioEntrada() {
		return precioEntrada;
	}

	public void setPrecioEntrada(double precioEntrada) {
		this.precioEntrada = precioEntrada;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getIdCine() {
		return idCine;
	}

	public void setIdCine(String idCine) {
		this.idCine = idCine;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public String getClavePrimaria() {
		return clavePrimaria;
	}

	public void setClavePrimaria(String clavePrimaria) {
		this.clavePrimaria = clavePrimaria;
	}
	
	
	
	
	
}
