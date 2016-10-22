package abd.p1.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class MTipoPetAmistad extends Mensaje{

	public MTipoPetAmistad(Date fecha, Usuario emisor, Usuario receptor) {
		super(fecha, emisor, receptor);
		// TODO Auto-generated constructor stub
	}
	
	public MTipoPetAmistad(){
		super();
	}
	
	public List<String> cuerpoMensaje(MTipoPetAmistad m) {
		List<String> lista = new LinkedList<String>();
		lista.add("[" + m.getFechaYHora() + "]" + " Solicitud de amiastad de: ");
		lista.add(m.getEmisor().getNombre());
		
		return lista;
	}

}
