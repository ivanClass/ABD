package abd.p1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ElementoListaUsuario extends JPanel{
	private byte[] foto;
	private String nombre;
	private int edad;
	
	private JLabel fotoPerfil;
	private JLabel labelNombre;
	private JLabel labelEdad;
	
	public ElementoListaUsuario(){
		this.initGui();
	}
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		JPanel datos = new JPanel();
		
		datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));
	
		this.fotoPerfil = new JLabel();
		this.fotoPerfil.setPreferredSize(new Dimension(100, 100));
		this.fotoPerfil.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(this.fotoPerfil, BorderLayout.WEST);
		this.labelNombre = new JLabel();
		this.labelNombre.setFont(new Font("Dialog", Font.BOLD, 20));
		datos.add(this.labelNombre);
		this.labelEdad = new JLabel();
		datos.add(this.labelEdad);
		Border marginT = new EmptyBorder(10, 3, 0, 0);
		datos.setBorder(marginT);
		datos.setBackground(new Color(0, 0, 0, 0));
		this.add(datos, BorderLayout.CENTER);
	}
	
	
	
	public byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
		ImageIcon icono = null;
		
		if(this.foto != null){
			icono = new ImageIcon(this.foto);
		}
		else{
			
			java.net.URL url_icono = null;
			url_icono = getClass().getResource("sinFoto.png");
			icono = new ImageIcon(url_icono);
		}
		
		fotoPerfil.setIcon(icono);
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
		this.labelNombre.setText(nombre);
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
		if(edad != -1)
			this.labelEdad.setText(Integer.toString(edad)  + " años");
		else{
			this.labelEdad.setText("Edad desconocida");
		}
	}
	
	
}
