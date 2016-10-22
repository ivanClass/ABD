package abd.p1.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Mensaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMensaje;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaYHora;
	
	
	private boolean leido;
	
	@ManyToOne
	private Usuario emisor;
	
	@ManyToOne
	private Usuario receptor;
	
	public Mensaje(){
		
	}
	
	public Mensaje(Date fecha, Usuario emisor, Usuario receptor){
		this.fechaYHora = fecha;
		this.leido = false;
		this.emisor = emisor;
		this.receptor = receptor;
	}
	
	public int getIdMensaje() {
		return idMensaje;
	}
	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}
	public Date getFechaYHora() {
		return fechaYHora;
	}
	public void setFechaYHora(Date fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	public boolean isLeido() {
		return leido;
	}
	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	public Usuario getEmisor() {
		return emisor;
	}
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}
	public Usuario getReceptor() {
		return receptor;
	}
	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}
}
