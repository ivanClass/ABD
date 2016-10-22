package p1admin.model;

import java.util.ArrayList;
import java.util.List;

public class Aficion {
	private int idAficion;
	private String descripcion;
	private List<Usuario> listaUsuarios;
	

	public Aficion(){
		this.listaUsuarios = new ArrayList<Usuario>();
	}
	public int getIdAficion() {
		return idAficion;
	}
	
	public void setIdAficion(int idAficion) {
		this.idAficion = idAficion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
}
