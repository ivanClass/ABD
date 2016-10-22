package abd.p1.model;

public class PreguntaResumen {
	private String tituloPregunta;
	private Integer idPregunta;
	private int numOpcionesPregunta;
	
	public PreguntaResumen(){
		
	}
	
	public PreguntaResumen(String t, Integer i, int n) {
		this.tituloPregunta = t;
		this.idPregunta = i;
		this.numOpcionesPregunta = n; 
	}

	public String getTituloPregunta() {
		return tituloPregunta;
	}

	public void setTituloPregunta(String tituloPregunta) {
		this.tituloPregunta = tituloPregunta;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public int getNumOpcionesPregunta() {
		return numOpcionesPregunta;
	}

	public void setNumOpcionesPregunta(int numOpcionesPregunta) {
		this.numOpcionesPregunta = numOpcionesPregunta;
	}
}
