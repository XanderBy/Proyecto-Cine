package modelo.POJOs;

public class Sala {
	
	//VARIABLES Y CONSTANTES
	
	private String idSalaCine;
	private int numeroButacas;
	private static int codigoSala=0;
	
	//CONSTRUCTORES
	
	public Sala(){
		codigoSala=codigoSala++;
	}
	
	public Sala(String auditoriumCineId, int seatsNumber){
		idSalaCine=auditoriumCineId;
		numeroButacas=seatsNumber;
		codigoSala=codigoSala++;
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

	public static int getCodigoSala() {
		return codigoSala;
	}
	
	public static void setCodigoSala(int codigoSala) {
		Sala.codigoSala = codigoSala;
	}
	
	//MÉTODO TOSTRING

	@Override
	public String toString() {
		return "La sala "+ getIdSalaCine()+ " y codigo "+ getCodigoSala()+ ", tiene "+getNumeroButacas()+" butacas";
	}

}
