package p1admin.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String correo;
	private String psw;
	private String descripcion;
	private Date fechaNacimiento;
	private byte[] imagenPerfil;
	private String nombre;
	private String genero;
	private String interes;
	private String latitud;
	private String longitud;
	
	private List<Aficion> listaAficiones;
	private List<Usuario> listaAmigos;
	private List<Envia> listaEnvios;
	private List<Contesta> listaContestaciones;
	
	public Usuario(){
		this.listaAficiones = new ArrayList<Aficion>();
		this.listaAmigos = new ArrayList<Usuario>();
		this.listaEnvios = new ArrayList<Envia>();
		this.listaContestaciones = new ArrayList<>();
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
	}

	public byte[] getImagenPerfil() {
		return imagenPerfil;
	}

	public void setImagenPerfil(byte[] imagenPerfil) {
		this.imagenPerfil = imagenPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getInteres() {
		return interes;
	}

	public void setInteres(String interes) {
		this.interes = interes;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public List<Aficion> getListaAficiones() {
		return listaAficiones;
	}

	public void setListaAficiones(List<Aficion> listaAficiones) {
		this.listaAficiones = listaAficiones;
	}

	public List<Usuario> getListaAmigos() {
		return listaAmigos;
	}

	public void setListaAmigos(List<Usuario> listaAmigos) {
		this.listaAmigos = listaAmigos;
	}

	public List<Envia> getListaEnvios() {
		return listaEnvios;
	}

	public void setListaEnvios(List<Envia> listaEnvios) {
		this.listaEnvios = listaEnvios;
	}

	public List<Contesta> getListaContestaciones() {
		return listaContestaciones;
	}

	public void setListaContestaciones(List<Contesta> listaContestaciones) {
		this.listaContestaciones = listaContestaciones;
	}
}
