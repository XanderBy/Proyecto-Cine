package modelo.POJOs;

public class Sala {
	
	//VARIABLES Y CONSTANTES
	
	private String idSalaCine, nombreSala;
	private int numeroButacas;
	
	//CONSTRUCTORES
	
	public Sala(){

	}
	
	public Sala(String nameSala, int seatsNumber){
		idSalaCine="";
		nombreSala=nameSala;
		numeroButacas=seatsNumber;
	}
	
	//ACCEDENTES Y MUTADORES

	public String getIdSalaCine() {
		return idSalaCine;
	}

	public void setIdSalaCine(String idSalaCine) {
		this.idSalaCine = idSalaCine;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public int getNumeroButacas() {
		return numeroButacas;
	}

	public void setNumeroButacas(int numeroButacas) {
		this.numeroButacas = numeroButacas;
	}

	//METODO TOSTRING

	@Override
	public String toString() {
		return "La sala que pertenece al cine "+getIdSalaCine()+" con nombre"+getNombreSala()+" tiene "+getNumeroButacas();
	}

	

}
