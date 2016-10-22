package p1admin.model;

public class Contesta {
	private Usuario usuario;
	private Opcion opcion;
	private int relevancia;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Opcion getOpcion() {
		return opcion;
	}
	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}
	public int getRelevancia() {
		return relevancia;
	}
	public void setRelevancia(int relevancia) {
		this.relevancia = relevancia;
	}
}
