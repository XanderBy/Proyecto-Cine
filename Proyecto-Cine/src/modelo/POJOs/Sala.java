package modelo.POJOs;

public class Sala {
	
	//VARIABLES Y CONSTANTES
	
	private String nombreSala, idSalaCine;
	private int numeroButacas;
	private static int codigoSala=0;
	
	//CONSTRUCTORES
	
	Sala(){
		codigoSala=codigoSala++;
	}
	
	Sala(String auditoriumName, String auditoriumCineId, int seatsNumber){
		nombreSala=auditoriumName;
		idSalaCine=auditoriumCineId;
		numeroButacas=seatsNumber;
		codigoSala=codigoSala++;
	}
	
	//ACCEDENTES Y MUTADORES

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

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

	public static int getCodigoSala() {
		return codigoSala;
	}
	
	//MÉTODO TOSTRING
	
	@Override
	public String toString() {
		return "La sala "+ getNombreSala() + " con Id respecto al cine "+ getIdSalaCine()+ " y codigo "+ this.getCodigoSala()+ ", tiene "+getNumeroButacas()+" butacas";
	}

}
