package abd.p1.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abd.p1.model.Usuario;

public class UsuarioCellRender extends ElementoListaUsuario implements ListCellRenderer<Usuario>{

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Usuario> list, Usuario value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		if(value.getImagenPerfil()!=null){
			this.setFoto(value.getImagenPerfil());
		}
		else{
			this.setFoto(null);
		}
		
		this.setNombre(value.getNombre());
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if(value.getFechaNacimiento() != null){
			this.setEdad(value.edad());
		}
		else{
			this.setEdad(-1);
		}

		this.setOpaque(true);
		
		if(isSelected){
			this.setBackground(Color.cyan);
		}
		else{
			this.setBackground(Color.WHITE);
		}
		
		return this;
	}
	
}
