package modelo.POJOs;

public class Sala {
	
	//VARIABLES Y CONSTANTES
	
	private String nombreSala, nombreCinePertenece;
	private int numeroButacas;
	
	//CONSTRUCTORES
	
	public Sala(){

	}
	
	public Sala(String auditoriumName, int seatsNumber){
		nombreSala=auditoriumName;
		nombreCinePertenece="";
		numeroButacas=seatsNumber;
	}
	
	//ACCEDENTES Y MUTADORES

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public String getNombreCinePertenece() {
		return nombreCinePertenece;
	}

	public void setNombreCinePertenece(String nombreCinePertenece) {
		this.nombreCinePertenece = nombreCinePertenece;
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
		return "La sala "+nombreSala+" pertenece al cine "+nombreCinePertenece+" y tiene "+numeroButacas+ "butacas";
	}



}
