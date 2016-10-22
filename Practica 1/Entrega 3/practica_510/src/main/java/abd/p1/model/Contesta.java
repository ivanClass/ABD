package abd.p1.model;

import javax.persistence.*;


@Entity
@IdClass(ClaveContesta.class)
public class Contesta {
	@Id
	@ManyToOne
	private Usuario usuario;
	
	@Id
	@ManyToOne
	private Opcion opcion;
	
	private int relevancia;
	
	public int getRelevancia() {
		return relevancia;
	}
	public void setRelevancia(int relevancia) {
		this.relevancia = relevancia;
	}
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

}
