package abd.p1.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import abd.p1.controller.Controlador;
import abd.p1.model.Usuario;

public class Sesion extends JPanel{
	private JPanel norte;
	private JPanel sur;
	
	private JLabel labelUsuario;
	private JLabel labelContrase;
	private JTextField textUsuario;
	private JPasswordField textContrase;
	private JButton botonAceptar;
	private JButton botonNuevoUsu;
	
	private Controlador controlador;
	private MainFrame ventana;
	
	
	public Sesion(Controlador contr, MainFrame ventana){
		this.controlador = contr;
		this.ventana = ventana;
		initGUI();
		confEventos();
	}

	private void initGUI(){
		this.setLayout(new BorderLayout());
		
		this.norte = this.panelNorte();
		this.add(this.norte, BorderLayout.NORTH);
		
		this.sur = this.panelSur();
		this.add(this.sur, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(400, 140));
		
	}
	
	private JPanel panelNorte(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints cons;
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(10, 10, 0, 0);
		cons.gridx = 0; //columna
		cons.gridy = 0; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.labelUsuario = new JLabel("Dirección de correo: ");
		panel.add(this.labelUsuario, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(5, 10, 0, 0);
		cons.gridx = 0; //columna
		cons.gridy = 1; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.labelContrase = new JLabel("Contraseña: ");
		panel.add(this.labelContrase, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(10, 10, 5, 10);
		cons.gridx = 1; //columna
		cons.gridy = 0; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 30;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.textUsuario = new JTextField();
		panel.add(this.textUsuario, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(5, 10, 0, 10);
		cons.gridx = 1; //columna
		cons.gridy = 1; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 30;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.textContrase = new JPasswordField();
		panel.add(this.textContrase, cons);
		
		return panel;
	}
	
	private JPanel panelSur(){
		JPanel panel = new JPanel();
		
		this.botonAceptar = new JButton("Aceptar");
		panel.add(this.botonAceptar);
		
		this.botonNuevoUsu = new JButton("Nuevo usuario");
		panel.add(this.botonNuevoUsu);
		
		return panel;
	}
	
	private void confEventos() {
		
		this.botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controlador.iniciarSesion(textUsuario.getText(), textContrase.getText())){
					meteAlaPila();
					remove();
				
					Usuario aux = controlador.dameUsuario(textUsuario.getText());
					Principal prGUI = new Principal(controlador, ventana, aux);
					ventana.add(prGUI);
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				
				}
				else{
					JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
					textContrase.setText("");
				}
			}
		});
		
		this.botonNuevoUsu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textUsuario.getText().length() > 0 && textContrase.getText().length() > 0 ){
					if(controlador.comprobarExisteUsu(textUsuario.getText())){
						JOptionPane.showMessageDialog(null,"El usuario ya existe");
						textUsuario.setText("");
					}
					else {
						meteAlaPila();
						remove();
						Usuario u = new Usuario();
						u.setCorreo(textUsuario.getText());
						u.setPsw(textContrase.getText());
						controlador.setUsuario(u);
						EditarUsuario euGUI = new EditarUsuario(controlador, u, ventana);
						//PerfilUsuario euGUI = new PerfilUsuario(controlador, u, ventana);
						ventana.add(euGUI);
						revalidate();
						ventana.pack();
						ventana.setLocationRelativeTo(null);
					}
				}
				
			}
		});
		
	}
	
	private void remove(){
		this.ventana.remove(this);
	}

	private void meteAlaPila(){
		this.ventana.metePila(this);
	}
}
