package abd.p1.model;

public enum Generos{
	HOMBRE("HOMBRE", "♂"),
	MUJER("MUJER", "♀"),
	AMBOS("AMBOS", "∞");
	
	private String nombre;
	private String simbolo;
	
	private Generos(String nomb,String simb){
		this.nombre = nomb;
		this.simbolo = simb;
	}
	

	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getSimbolo(){
		return this.simbolo;
	}
}

