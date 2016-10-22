package abd.p1.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

import abd.p1.view.Observer;



@Entity
public class Usuario {
	
	@Id
	@Column(nullable = false, length = 30)
	private String correo;
	
	@Column(nullable = false, length = 16)
	private String psw;
	
	
	private String descripcion;
	
	@Temporal(TemporalType.DATE) //SOLO QUEREMOS LA FECHA Y NO LA HORA
	private Date fechaNacimiento;
	
	@Lob
	private byte[] imagenPerfil;
	
	@Column(nullable = false, length = 40)
	private String nombre;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Generos genero;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Generos interes;
	
	private double latitud;
	
	private double longitud;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> listaAficiones;
	
	@ManyToMany(cascade = CascadeType.ALL)  //Un usuario tiene una lista de amigos, pero ese usuario tambien tiene otra lista de amigos
	private Set<Usuario> listaAmigos;
	
	@OneToMany( mappedBy = "usuario", cascade = CascadeType.ALL)  //Un usuario tiene una lista de contestaciones, pero una contestacion es de un solo usuario
	private List<Contesta> listaContestaciones;
	
	@OneToMany( mappedBy = "emisor", cascade = CascadeType.ALL)
	private List<Mensaje> listaMensajes;
	
	@Transient
	private ArrayList<Observer> obs;
	
	
	public Usuario(){
		this.listaAficiones = new ArrayList<String>();
		this.listaMensajes = new ArrayList<Mensaje>();
		this.listaContestaciones = new ArrayList<>();
		this.listaAmigos = new TreeSet<Usuario>();
		this.obs = new ArrayList<Observer>();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		
		for (Observer o : obs) {
			int anios = edad();
			o.notifyEdadCh(anios);
		}
	}

	public byte[] getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
		for (Observer o : obs) {
			o.notifyAvaCh(imagenPerfil);
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		for (Observer o : obs) {
			o.notifyNameCh(this.nombre);
		}
	}

	public Generos getGenero() {
		return genero;
	}

	public void setGenero(Generos genero) {
		this.genero = genero;
		for (Observer o : obs) {
			o.notifySexCh(genero.toString());
		}

	}

	public Generos getInteres() {
		return interes;
	}

	public void setInteres(Generos interes) {
		this.interes = interes;
		for (Observer o : obs) {
			o.notifyPrefCh(interes.toString());
		}
	}

	public double getLatitud() {
		return latitud;
		
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public List<String> getListaAficiones() {
		return listaAficiones;
	}

	public void setListaAficiones(List<String> listaAficiones) {
		this.listaAficiones = listaAficiones;
	}

	public Set<Usuario> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(Set<Usuario> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}
	

	public List<Mensaje> getListaMensajes() {
		return listaMensajes;
	}

	public void setListaEnvios(List<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	public List<Contesta> getListaContestaciones() {
		return listaContestaciones;
	}

	public void setListaContestaciones(List<Contesta> listaContestaciones) {
		this.listaContestaciones = listaContestaciones;
	}
	
	public void addObserver(Observer o){
		this.obs.add(o);
	}
	
	public void addAficion(String a){
		this.listaAficiones.add(a);
		for (Observer o : obs) {
			o.notifyAddAf(a);
		}
	}
	public void addAficion(String a, int index){
		this.listaAficiones.add(index,a);

	}
	
	public void addMensaje(Mensaje m){
		this.listaMensajes.add(m);
	}
	
	public int edad(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		
		String fechaact = dateFormat.format(cal.getTime());
		int anios = cal.getTime().getYear() - fechaNacimiento.getYear();
		if(this.fechaNacimiento.getMonth() > cal.getTime().getMonth()){
			anios--;
		}
		
		return anios;
	}
	
	public void asignaCoordenadasRandom(){
		 this.latitud = Math.random()*(41.2-40)+40;
		 this.longitud = Math.random()*(4.5-3)+3;
	}
	
}
