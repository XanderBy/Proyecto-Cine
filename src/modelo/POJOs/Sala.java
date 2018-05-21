package modelo.POJOs;

public class Sala {
	
	//VARIABLES Y CONSTANTES
	
	private String idSalaCine, codigoSala;
	private int numeroButacas;
	
	//CONSTRUCTORES
	
	public Sala(){

	}
	
	public Sala(String auditoriumCineId, int seatsNumber){
		idSalaCine=auditoriumCineId;
		numeroButacas=seatsNumber;
		codigoSala=idSalaCine;
	}
	
	//ACCEDENTES Y MUTADORES

	public String getIdSalaCine() {
		return idSalaCine;
	}

	public void setIdSalaCine(String idSalaCine) {
		this.idSalaCine = idSalaCine;
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
		return "La sala "+ getIdSalaCine()+ " tiene "+getNumeroButacas()+" butacas";
	}

}
