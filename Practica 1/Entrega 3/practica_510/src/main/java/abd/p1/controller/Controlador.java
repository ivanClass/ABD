package abd.p1.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import abd.p1.bd.Dao;
import abd.p1.model.Aficion;
import abd.p1.model.Contesta;
import abd.p1.model.Generos;
import abd.p1.model.MTipoPetAmistad;
import abd.p1.model.MTipoPregunta;
import abd.p1.model.MTipoTexto;
import abd.p1.model.Mensaje;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;
import abd.p1.view.Observer;

public class Controlador {
	private Usuario usuario;
	private Dao dao;
	
	public Controlador(Dao dao){
		this.dao = dao;
	}
	
	public void setUsuario(Usuario u){
		this.usuario = u;
	}
	
	public void addObservador(Observer v){
		this.usuario.addObserver(v);
	}
	
	public List<?> cargarListaUsuarioInicio(){
		return this.dao.cargarListaUsuarioInicio(usuario.getCorreo());
	}
	
	public void cambiameNombre(String n){
		this.usuario.setNombre(n);
	}
	
	public boolean iniciarSesion(String usu, String psw){
		List<?> list = this.dao.iniSesEsCorrecto(usu);
		boolean ok = false;
		
		if(!list.isEmpty()){
			String pswBD = (String) list.get(0);
			
			if(psw.equals(pswBD)){
				ok = true;
			}
		}
		
		return ok;
	}
	
	public boolean comprobarExisteUsu(String usu){
		List<?> list = this.dao.existeUsu(usu);
		return !list.isEmpty(); //si la lista no está vacía, devolvemos true ya que existe dicho usuario  
	}

	public List<?> cargarListasUsuarioFiltro(String text) {
		return this.dao.cargarListaUsuarioFiltro(text, this.usuario.getCorreo(), usuario.getInteres());
	}
	
	public void addOrUpdateUsuario(Usuario usuario){
		this.dao.addOrUpdateUsuario(usuario);
	}
	
	public Usuario dameUsuario(String correo){
		return this.dao.dameUsuario(correo);
	}
	
	public List<?> cargar20PreguntasMasValoradas(){
		return this.dao.cargar20PreguntasMasValoradas();
	}
	
	public Pregunta damePregunta(Integer id){
		return this.dao.damePregunta(id);
	}
	
	public Contesta dameContestacion(Opcion p){
		List<?> l =  this.dao.dameContestacion(usuario, p);
		
		if(l.isEmpty()){
			Contesta c = null;
			return c;
		}
		else
			return (Contesta) l.get(0);
	}
	
	public void respuestaPregunta(Pregunta p, Opcion o, int r){
		
		Contesta cbd = dameContestacion(o);
		Contesta c = new Contesta();
		c.setOpcion(o);
		c.setRelevancia(r);
		c.setUsuario(this.usuario);
		
		
		if(cbd != null){
			this.dao.actualizarContestacion(cbd, c);
		}
		else{
			this.dao.guardarContestacion(c);
		}

	}
	
	
	public Pregunta damePreguntaAleatoria(){
		Pregunta p = new Pregunta();
		p = (Pregunta) this.dao.damePreguntaAleatoria().get(0);
		
		return p;
	}
	
	public void setImgUsu(byte[] img){
		usuario.setImagenPerfil(img);
	}
	public void setNombreUsu(String s){
		usuario.setNombre(s);
	}
	
	public void setFechaUsu(Date d){
		usuario.setFechaNacimiento(d);
	}
	
	public void setDescripUsu(String s){
		usuario.setDescripcion(s);
	}
	
	public void addAficinUsu(String af){
		usuario.addAficion(af);
	}
	
	public void addAficinUsu(int index, String af){
		usuario.addAficion(af,index);
	}
	
	public void editAficionUsu(String s, int index){
		usuario.getListaAficiones().set(index, s);
		
	}
	public void rmAficionUsu(int index){
		usuario.getListaAficiones().remove(index);
	}
	
	public void setGeneroUsu(Generos g){
		usuario.setGenero(g);
	}
	
	public void setInteresUsu(Generos g){
		usuario.setInteres(g);
	}
	
	public void setPswUsu(String s){
		usuario.setPsw(s);
	}
	
	public void coordenadasRandomUsu(){
		usuario.asignaCoordenadasRandom();
	}
	
	public void updateUsuario(){
		dao.merge(this.usuario);
	}
	
	public List<?> cargar20UsuariosMasCercanos(String filtro){
		List<?> lista = null;
		if(filtro == null)
			lista = dao.cargar20UsuariosMasCercanos(this.usuario);
		else
			lista = dao.cargar20UsuariosMasCercanos(this.usuario, filtro);
		
		
		return lista;
	}
	public List<?> cargarAmigosOrdenDistancia(){
		List<?> lista = null;
		lista = dao.cargarAmigosOrdenDistancia(this.usuario);
		return lista;
	}
	
	public List<?> cargarAmigosOrdenDistancia(String filtro){
		List<?> lista = null;
		String filtroAux = "";
		if(filtro != null)
			filtroAux = filtro;
		
		lista = dao.cargarAmigosOrdenDistancia(this.usuario, filtroAux);
		
		return lista;
	}
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public int calculaCompatibilidad(Usuario u){
		return dao.compatibilidad(this.usuario, u);
	}
	public void finish(){
		this.dao.finish();
	}
	
	public void enviaPetAmistad(Usuario receptor){
		usuario.setListaAmigos(this.dao.getListaAmigos(usuario));
		usuario.getListaAmigos().add(receptor);
		
		receptor.setListaAmigos(this.dao.getListaAmigos(receptor));
		receptor.getListaAmigos().add(usuario);
		
		this.dao.addAmigo(usuario, receptor);
	}
	
	public List dameAficionesComunes(Usuario u){

		List afUsuLog = new ArrayList<>();
		for (String a : this.usuario.getListaAficiones()) {
			afUsuLog.add(a);
		}
		
		List afUsuVer = new ArrayList<>();
		for (String a : u.getListaAficiones()) {
			afUsuVer.add(a);
		}
		
		afUsuLog.retainAll(afUsuVer);
		
		return afUsuLog;
	}
	
	public void invitarPregunta(Pregunta preg, Usuario destino){
		MTipoPregunta mensajePregunta= new MTipoPregunta(preg, this.usuario, destino);
		List<Mensaje> lista = this.dao.cargarTodosMensajes(this.usuario.getCorreo());
		this.usuario.setListaEnvios(lista);
		this.usuario.addMensaje(mensajePregunta);
		this.dao.addPeticionPregunta(mensajePregunta);
		
	}

	public List cargarMensajes(String receptor) {
		// TODO Auto-generated method stub
		List<MTipoTexto> lista;
		
		lista = this.dao.cargarMensajesChat(this.usuario.getCorreo(), receptor);
		
		return lista;
	}

	public void addMensajeChat(Usuario usu, String texto) {
		MTipoTexto mt = new MTipoTexto(this.usuario, usu, texto);
		List<Mensaje> lista = this.dao.cargarTodosMensajes(this.usuario.getCorreo());
		this.usuario.setListaEnvios(lista);
		this.usuario.addMensaje(mt);
		this.dao.addMensajeChat(mt);
	}
	
	public List<Mensaje> cargarTodosLosMensajesNoLeidos(){
		return this.dao.cargarTodosLosMensajesNoLeidos(this.usuario.getCorreo());
	}
	
	public void marcarLeidos(){//pasar algo??
		List<Mensaje> lista = this.dao.cargarTodosMensajes(this.usuario.getCorreo());
		for(Mensaje mensaje: lista){
			if(!mensaje.isLeido()){
				mensaje.setLeido(true);
			}
		}
		this.usuario.setListaEnvios(lista);
	}
/*
	public void closeSession(){
		this.dao.closeSession();
	}
	*/
}
