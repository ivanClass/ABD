package abd.p1.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ElementoListaPregunta extends JPanel{
	private String tituloPreg;
	private int numOpciones;
	
	private JLabel labelTituloPreg;
	private JLabel labelNumOpc;
	
	public ElementoListaPregunta(){
		this.initGui();
	}
	
	private void initGui(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.labelTituloPreg = new JLabel();
		this.labelTituloPreg.setFont(new Font("Dialog", Font.BOLD, 20));
		Border marginT = new EmptyBorder(3, 3, 3, 3);
		this.labelTituloPreg.setBorder(marginT);
		this.add(this.labelTituloPreg);
		
		this.labelNumOpc = new JLabel();
		Border marginN = new EmptyBorder(3, 15, 3, 3);
		this.labelNumOpc.setBorder(marginN);
		this.add(this.labelNumOpc);
		
		this.setTituloPreg(this.tituloPreg);
		this.setNumOpciones(this.numOpciones);
	}

	public String getTituloPreg() {
		return tituloPreg;
	}

	public void setTituloPreg(String tituloPreg) {
		this.tituloPreg = tituloPreg;
		this.labelTituloPreg.setText(tituloPreg);
	}

	public int getNumOpciones() {
		return numOpciones;
	}

	public void setNumOpciones(int numOpciones) {
		this.numOpciones = numOpciones;
		this.labelNumOpc.setText(Integer.toString(numOpciones) + " opciones");
	}	
}