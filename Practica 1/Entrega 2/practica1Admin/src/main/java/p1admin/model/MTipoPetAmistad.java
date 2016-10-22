package p1admin.model;

public class MTipoPetAmistad extends Mensaje{
	private String texto;
	private boolean respuesta;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public boolean isRespuesta() {
		return respuesta;
	}
	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}
	
}
