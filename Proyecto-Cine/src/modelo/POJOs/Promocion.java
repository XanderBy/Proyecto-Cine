package modelo.POJOs;

public class Promocion {
	
	//VARIABLES
	
	private String descripcionPromo;
	private int descuentoPromo;
	
	//CONSTRUCTORES
	
	public Promocion(){
		
	}
	
	public Promocion(String promoDescription, int promoDiscount){
		
		descripcionPromo=promoDescription;
		descuentoPromo=promoDiscount;
		
	}
	
	//ACCEDENTES Y MUTADORES

	public String getDescripcionPromo() {
		return descripcionPromo;
	}

	public void setDescripcionPromo(String descripcionPromo) {
		this.descripcionPromo = descripcionPromo;
	}

	public int getDescuentoPromo() {
		return descuentoPromo;
	}

	public void setDescuentoPromo(int descuentoPromo) {
		this.descuentoPromo = descuentoPromo;
	}
	
	//M�TODO TOSTRING
	
	@Override
	
	public String toString() {
		return "Descripcion de la promocion: ".concat(getDescripcionPromo()) + "\nDescuento aplicable: "+ getDescuentoPromo();
	}
	
	
	
	

}
