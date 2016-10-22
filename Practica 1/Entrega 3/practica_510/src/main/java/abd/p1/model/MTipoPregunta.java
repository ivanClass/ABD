package abd.p1.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
public class MTipoPregunta extends Mensaje{
	
	@ManyToOne                                      
	private Pregunta pregunta;
	
	public MTipoPregunta(Pregunta p, Usuario emisor, Usuario receptor){
		super(new Date(), emisor, receptor);
		this.pregunta = p;
	}
	
	public MTipoPregunta(){
		super();
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public List<String> cuerpoMensaje(MTipoPregunta m) {
		List<String> lista = new LinkedList<String>();
		lista.add("[" + m.getFechaYHora() + "]" + m.getEmisor().getNombre() + "te ha invitado a responder a la regunta: ");
		lista.add(m.pregunta.getEnunciado());
		
		return lista;
	}
}
