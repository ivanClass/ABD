package abd.p1.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class MTipoTexto extends Mensaje{
	public MTipoTexto(Usuario emisor, Usuario receptor, String texto) {
		super(new Date(new Timestamp(0).getTime()), emisor, receptor);
		this.texto = texto;
	}
	
	public MTipoTexto(){
		super();
	}
	

	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public List<String> cuerpoMensaje(MTipoTexto m) {
		List<String> lista = new LinkedList<String>();
		lista.add("[" + m.getFechaYHora() + "] " + m.getEmisor().getNombre() + " escribió: ");
		lista.add(m.getTexto());
		
		return lista;
	}
}
