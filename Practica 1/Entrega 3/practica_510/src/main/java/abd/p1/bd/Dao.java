package abd.p1.bd;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import abd.p1.model.Contesta;
import abd.p1.model.Generos;
import abd.p1.model.MTipoPregunta;
import abd.p1.model.MTipoTexto;
import abd.p1.model.Mensaje;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;

public class Dao {	
	private SessionFactory sesFac;

	
	public Dao(SessionFactory sf){
		this.sesFac = sf;
	}
	
	private List executeHQLQuery(String hql){     //A VER QUE DEVUELVE ESTOO (RETURN CUANDO HAY FALLO)
		Session s = this.sesFac.openSession();
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			Query consulta = s.createQuery(hql);
			resultList = consulta.list();
			tx.commit();
		}
		catch(Exception e){
			System.out.println("MECCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC-> " + e.getMessage());
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;
	}
	
	public List consultarTodosLosUsuarios(){
		String hql = "FROM Usuario";
		List list = executeHQLQuery(hql);
		
		return list;
	}
	
	public List iniSesEsCorrecto(String usu){
		
		String hql = "SELECT u.psw FROM Usuario u WHERE u.correo = " + "'" + usu + "'";
		List list = executeHQLQuery(hql);
		
		return list;
	}
	
	public List existeUsu(String usu){
		String hql = "SELECT u.correo FROM Usuario u WHERE u.correo = " + "'" + usu + "'";
		List list = executeHQLQuery(hql);
		
		return list;
	}
	
	public List cargarListaUsuarioInicio(String usu){
		String hql = "SELECT u.imagenPerfil, u.nombre, u.fechaNacimiento FROM Usuario u WHERE u.correo != " + "'" + usu + "'";
		List lista = executeHQLQuery(hql);
		
		return lista;
	}

	public List cargarListaUsuarioFiltro(String text, String usu, Generos interes) {
		String hql = "SELECT u.imagenPerfil, u.nombre, u.fechaNacimiento, u.correo FROM Usuario u WHERE (u.nombre LIKE "  
				+ "'%" + text + "%') AND (u.correo != " + "'" + usu + "') AND (u.genero = " + "'" + interes + "' AND '" 
							+ interes + "' != 'AMBOS') OR ('" + interes + "' = 'AMBOS' AND u.correo != " + "'" + usu + "') ";
		List lista = executeHQLQuery(hql);
		
		return lista;
	}
	
	//PONER LOS TRY CATCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void addOrUpdateUsuario(Usuario usuario){
		Session s = sesFac.openSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(usuario);
		tx.commit();
		s.close();
	}
	
	public void addAmigo(Usuario emisor, Usuario receptor){
		Session s = sesFac.openSession();
		Transaction tx = s.beginTransaction();
		s.saveOrUpdate(receptor);
		s.saveOrUpdate(emisor);
		tx.commit();
		s.close();
	}
	
	public Usuario dameUsuario(String correo){
		Session s = sesFac.openSession();
		Transaction tr = s.beginTransaction();	
		Usuario u = s.get(Usuario.class, correo);
		tr.commit();
		s.close();
		
		return u;
	}
	
	public List cargar20PreguntasMasValoradas(){
		String   hql = "SELECT p.enunciado, p.id, COUNT(DISTINCT o) FROM Pregunta p JOIN FETCH Opcion o ON p.id = o.preguntaMadre JOIN FETCH Contesta c ON o.idOpcion = c.opcion GROUP BY p.id ORDER BY AVG(c.relevancia) DESC";
		Session s = this.sesFac.openSession();
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			Query consulta = s.createQuery(hql);
			consulta.setMaxResults(20);
			resultList = consulta.list();
			tx.commit();
		}
		catch(Exception e){
			System.out.println("MECCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC-> " + e.getMessage());
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;
	}

public List cargar20UsuariosMasCercanos(Usuario u){	
		Session s = this.sesFac.openSession();
		String haversine = "(6371 * acos(cos(radians(:latitud)) * cos(radians(u.latitud)) * cos(radians(u.longitud) - radians(:longitud)) + sin(radians(:latitud)) * sin(radians(u.latitud))))";
		String hql = "SELECT u.imagenPerfil, u.nombre, u.fechaNacimiento, u.correo  FROM Usuario u WHERE (u.correo !=:correo  AND (u.genero = :interes AND :interes != 'AMBOS') OR (:interes = 'AMBOS' AND u.correo != :correo)) ORDER BY " + haversine;
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			
			Query query = s.createQuery(hql);
			query.setParameter("longitud", u.getLongitud());
			query.setParameter("latitud", u.getLatitud());
			query.setParameter("correo", u.getCorreo());
			query.setParameter("interes", u.getInteres());

			query.setMaxResults(20);
			resultList = query.list();
			tx.commit();
		}
		catch(Exception e){
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;
	}

	public List cargar20UsuariosMasCercanos(Usuario u, String filtro){	
		Session s = this.sesFac.openSession();
		String haversine = "(6371 * acos(cos(radians(:latitud)) * cos(radians(u.latitud)) * cos(radians(u.longitud) - radians(:longitud)) + sin(radians(:latitud)) * sin(radians(u.latitud))))";
		String hql = "SELECT u.imagenPerfil, u.nombre, u.fechaNacimiento, u.correo  FROM Usuario u WHERE (u.nombre LIKE "  
				+ "'%" + filtro + "%')AND (u.correo !=:correo  AND (u.genero = :interes AND :interes != 'AMBOS') OR (:interes = 'AMBOS' AND u.correo != :correo)) ORDER BY " + haversine;
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			
			Query query = s.createQuery(hql);
			query.setParameter("longitud", u.getLongitud());
			query.setParameter("latitud", u.getLatitud());
			query.setParameter("correo", u.getCorreo());
			query.setParameter("interes", u.getInteres());
			query.setMaxResults(20);
			resultList = query.list();
			tx.commit();
		}
		catch(Exception e){
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;
	}

	public List cargarAmigosOrdenDistancia(Usuario u){
		Session s = this.sesFac.openSession();
		String haversine = "(6371 * acos(cos(radians(:latitud)) * cos(radians(a.latitud)) * cos(radians(a.longitud) - radians(:longitud)) + sin(radians(:latitud)) * sin(radians(a.latitud))))";
		String hql = "SELECT a.imagenPerfil, a.nombre, a.fechaNacimiento, a.correo  FROM Usuario u JOIN u.listaAmigos a WHERE u.correo=:correo ORDER BY " + haversine;
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			
			Query query = s.createQuery(hql);
			query.setParameter("longitud", u.getLongitud());
			query.setParameter("latitud", u.getLatitud());
			query.setParameter("correo", u.getCorreo());
			//query.setMaxResults(20);
			resultList = query.list();
			tx.commit();
		}
		catch(Exception e){
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;	
	}
	
	public List cargarAmigosOrdenDistancia(Usuario u, String filtro){
		Session s = this.sesFac.openSession();
		String haversine = "(6371 * acos(cos(radians(:latitud)) * cos(radians(a.latitud)) * cos(radians(a.longitud) - radians(:longitud)) + sin(radians(:latitud)) * sin(radians(a.latitud))))";
		String hql = "SELECT a.imagenPerfil, a.nombre, a.fechaNacimiento, a.correo  FROM Usuario u JOIN u.listaAmigos a WHERE (a.nombre LIKE "  
				+ "'%" + filtro + "%')AND u.correo=:correo ORDER BY " + haversine;
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			
			Query query = s.createQuery(hql);
			query.setParameter("longitud", u.getLongitud());
			query.setParameter("latitud", u.getLatitud());
			query.setParameter("correo", u.getCorreo());
			//query.setMaxResults(20);
			resultList = query.list();
			tx.commit();
		}
		catch(Exception e){
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;	
	}
	
	public Pregunta damePregunta(Integer id){
		return this.sesFac.openSession().get(Pregunta.class, id); 
	}
	
	public List dameContestacion(Usuario usu, Opcion p){
		String hql = "SELECT c FROM Contesta c WHERE c.usuario = :usu AND c.opcion.preguntaMadre = :pregunta";
		Session s = this.sesFac.openSession();
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			Query consulta = s.createQuery(hql);
			consulta.setParameter("usu", usu);
			consulta.setParameter("pregunta", p.getPreguntaMadre());
			resultList = consulta.list();
			tx.commit();
		}
		catch(Exception e){
			System.out.println("MECCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC-> " + e.getMessage());
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;
	
	}
	
	public void guardarContestacion(Contesta c){
		Transaction tx = null;
		Session s = this.sesFac.openSession();
		tx = s.beginTransaction();
		s.save(c);
		tx.commit();
		s.close();
		
	}
	
	public void actualizarContestacion(Contesta cViejo, Contesta cNuevo){
		int relev = cNuevo.getRelevancia();
		String correo = cNuevo.getUsuario().getCorreo();
		Opcion opN = cNuevo.getOpcion();
		int idOpV = cViejo.getOpcion().getIdOpcion();

		String hql = "UPDATE Contesta c SET c.relevancia = :relev, c.opcion = :opcion WHERE c.usuario.correo = :correo AND c.opcion.idOpcion = :idO";
		Session s = this.sesFac.openSession();
		Transaction tx = null;
		
		try{
			tx = s.beginTransaction();
			Query consulta = s.createQuery(hql);
			consulta.setParameter("relev", relev);
			consulta.setParameter("opcion", opN);
			consulta.setParameter("correo", correo);
			consulta.setParameter("idO", idOpV);
			consulta.executeUpdate();
			tx.commit();
		}
		catch(Exception e){
			System.out.println("MECCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC-> " + e.getMessage());
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
	}

public int compatibilidad(Usuario u1, Usuario u2){	
		Session s = this.sesFac.openSession();
	
		String hql = "SELECT SUM(c.relevancia) FROM Contesta c WHERE c.usuario = :usuario1 OR c.usuario = :usuario2 GROUP BY c.opcion.preguntaMadre HAVING COUNT(*) > 1";
		
		String hql2 = "SELECT SUM(c.relevancia) FROM Contesta c WHERE (c.usuario = :usuario1 OR c.usuario = :usuario2) GROUP BY c.opcion HAVING COUNT(*) > 1";
		Transaction tx = null;
		Double compatibilidad = (double) 0;
		int total = 0;
		int aciertos = 0;
		List resultList = null;
		List resultList2 = null;
		
		try{
			tx = s.beginTransaction();
			
			Query query = s.createQuery(hql);
			query.setParameter("usuario1", u1);
			query.setParameter("usuario2", u2);
			resultList = query.list();
			
			Query query2 = s.createQuery(hql2);
			query2.setParameter("usuario1", u1);
			query2.setParameter("usuario2", u2);
			resultList2 = query2.list();
			
			for (Object o : resultList) {
				total += Math.toIntExact((long) o);
			}
			for (Object o : resultList2) {
				aciertos += Math.toIntExact((long) o);
			}
			
			compatibilidad = (double) (((double)aciertos/(double)total)*100);
			
			
			tx.commit();
		}
		catch(Exception e){
			System.err.println(e.getMessage());
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		int k =compatibilidad.intValue();
		return compatibilidad.intValue();
	}
	
	public List damePreguntaAleatoria(){
		String hql = "SELECT p FROM Pregunta p ORDER BY RAND()";
		Session s = this.sesFac.openSession();
		Transaction tx = null;
		List resultList = null;
		
		try{
			tx = s.beginTransaction();
			Query consulta = s.createQuery(hql);
			consulta.setMaxResults(1);
			resultList = consulta.list();
			tx.commit();
		}
		catch(Exception e){
			System.out.println("MECCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC-> " + e.getMessage());
			if(tx != null)
				tx.rollback();
		}
		finally{
			s.close();
		}
		
		return resultList;
	}
	
	public void addPeticionPregunta(MTipoPregunta mtpreg){
		/*if(this.sUsuario != null)
			this.sUsuario.save(mtpreg);*/
		
		Transaction tx = null;
		Session s = this.sesFac.openSession();
		tx = s.beginTransaction();
		s.save(mtpreg);
		tx.commit();
		s.close();
	}
	
	public void addMensajeChat(MTipoTexto mt){
		Transaction tx = null;
		Session s = this.sesFac.openSession();
		tx = s.beginTransaction();
		s.save(mt);
		tx.commit();
		s.close();
	}
	
	
	
	
	
	public Usuario merge(Usuario u){
		//Session s = sesFac.openSession();
		//Transaction tx = s.beginTransaction();
		//if(this.sUsuario != null)
			//this.sUsuario.beginTransaction().commit();
		
		//tx.commit();
		//s.close();
		return u;
	}
	
    public void finish(){
    	this.sesFac.close();
    }
    
    public List<Mensaje> cargarTodosMensajes(String correo){
    	String hql = "SELECT u.listaMensajes FROM Usuario u WHERE u.correo = '" + correo + "'";
    	List<Mensaje> lista = executeHQLQuery(hql);
    	return lista;
    }

	public List cargarMensajesChat(String correo, String receptor) {
		
		String hql = "FROM MTipoTexto mt WHERE (mt.emisor.correo = " + "'" + correo + "'" + " AND mt.receptor.correo = " + "'" + receptor + "'"  + ") OR (mt.emisor.correo = " + "'" + receptor + "'" + " AND mt.receptor.correo = " + "'" + correo + "'" + ") ORDER BY mt.fechaYHora";
		List list = executeHQLQuery(hql);
		
		return list;
		
	}
	
	public Set<Usuario> getListaAmigos(Usuario u){
		String hql = "SELECT a FROM Usuario u JOIN u.listaAmigos a WHERE u.correo = '" + u.getCorreo() + "'";
		return new HashSet<>(executeHQLQuery(hql));
	}
	
	public List<Mensaje> cargarTodosLosMensajesNoLeidos(String nick){
		//String hql = "SELECT u.listaMensajes FROM Usuario u WHERE u.correo = '" + nick + "' AND u.listaMensajes.receptor.correo = '" + nick + "' AND u.listaMensajes.leido = '0'";
		String hql = "SELECT m FROM Mensaje m WHERE m.receptor.correo = '" + nick + "' AND m.leido = '0'";
		List<Mensaje> lista = executeHQLQuery(hql);
		
		return lista;
	}
	
	public void marcarLeidos(List<Mensaje> l){
		Transaction tx = null;
		Session s = this.sesFac.openSession();
		tx = s.beginTransaction();

		for(Mensaje mensaje: l){
			if(!mensaje.isLeido()){
				mensaje.setLeido(true);
			}
			
		}
		s.saveOrUpdate(l);
		tx.commit();
		s.close();
	}
}
