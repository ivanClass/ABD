package p1admin.model;

import java.sql.Date;

public abstract class Mensaje {
	private int idMensaje;
	private Date fechaYHora;
	private boolean leido;
	
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
}
