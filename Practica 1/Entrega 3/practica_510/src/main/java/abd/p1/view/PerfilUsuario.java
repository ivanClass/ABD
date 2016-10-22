package abd.p1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


import abd.p1.controller.Controlador;
import abd.p1.model.Aficion;
import abd.p1.model.MTipoTexto;
import abd.p1.model.Usuario;
import abd.p1.model.Utilidades;

public class PerfilUsuario extends JPanel{

	private Controlador controlador;
	private JTabbedPane panelPestanas;
	private Usuario usu;
	private MainFrame ventana;
	private JLabel img;
	private JLabel nombreL;
	private JLabel edadL;
	private JTextArea descripT;
	private JList<String> listAfic;
	private DefaultListModel<String> listModel;
	private JLabel sexoL2;
	private JLabel buscaL2;
	private JButton chSexBtn;
	private JLabel distancia;
	private JButton amChBtn;
	private JButton cancelBtn;
	private JLabel compInfo;
	private JLabel compInfoPorc;
	private JList<String> listAficCompat;
	private DefaultListModel<String> listModelCompat;
	private JLabel compInfoComunes;
	
	//pestaña chat
	private JPanel norteChat;
	private JPanel surChat;
	
	private JLabel enviarM;
	private JTextArea enviarMensaje;
	private JList<String> listaMensajes;
	private JScrollPane paneMensajes;
	private DefaultListModel<String> modelMensajes;
	
	
	public PerfilUsuario(Controlador c, Usuario u, MainFrame ventana){
		this.controlador = c;
		this.usu = u;
		this.ventana = ventana;
		
		initGUI();
		confEventos();
		
		if(this.usu != null){
			iniDatos();
		}
	}
	
	private void iniDatos(){
		ImageIcon icono = null;
		if(usu.getImagenPerfil() != null){
			ImageIcon fot = new ImageIcon(usu.getImagenPerfil());
			icono = new ImageIcon(fot.getImage().getScaledInstance(img.getPreferredSize().width, img.getPreferredSize().height, Image.SCALE_DEFAULT));
			img.setIcon(icono);
		}
		if(this.usu.getNombre() != null){
			nombreL.setText(this.usu.getNombre());
		}
		if(this.usu.getFechaNacimiento() != null){
			Integer edad = usu.edad();
			edadL.setText(edad.toString());
		}
		if(this.usu.getDescripcion() != null)
			descripT.setText(usu.getDescripcion());
		if(!this.usu.getListaAficiones().isEmpty()){
			for (String a : usu.getListaAficiones()) {
				listModel.addElement(a);
			}
		}
		if(this.usu.getGenero()!= null)
			sexoL2.setText(usu.getGenero().toString());
		
		if(this.usu.getGenero()!= null)
			buscaL2.setText(usu.getInteres().toString());
		Double d = Utilidades.haversine(this.usu.getLatitud(), this.usu.getLongitud(), controlador.getUsuario().getLatitud(), controlador.getUsuario().getLongitud());
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		distancia.setText("Distancia: "+ bd + "km");
		compInfoPorc.setText(controlador.calculaCompatibilidad(usu)+"%");
		
		List<String> list = controlador.dameAficionesComunes(usu);
		for (String af : list) {
			listModelCompat.addElement(af);
		}
		
		this.initChat();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());

		this.panelPestanas = new JTabbedPane();
		this.panelPestanas.addTab("Perfil", this.panelPerfilUsuario());
		this.panelPestanas.addTab("Compatibilidad", this.panelCompatibilidadUsuarios());
		this.panelPestanas.addTab("Chat", panelChat());
		this.add(this.panelPestanas, BorderLayout.NORTH);
		this.add(this.cuartoPanel(), BorderLayout.SOUTH);
	}
	

	private void confEventos(){
		amChBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.enviaPetAmistad(usu);
				remove();
				ventana.add(MainFrame.sacaPila());
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
			
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
				ventana.add(MainFrame.sacaPila());
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
			}
		});	
		
		this.enviarMensaje.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent tecla) {
				if(tecla.getKeyCode() == KeyEvent.VK_ALT){
					String texto = enviarMensaje.getText();
					if(texto != ""){
						modelMensajes.addElement("[" + new Date(new Timestamp(0).getTime()) + "] " + controlador.getUsuario().getNombre() + " escribió: ");
						modelMensajes.addElement(texto);
						controlador.addMensajeChat(usu, texto);
						enviarMensaje.setText("");
					}
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private JPanel panelPerfilUsuario(){
		JPanel panelPerfilUsu = new JPanel();
		panelPerfilUsu.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 2; 
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 10, 10, 10);
		panelPerfilUsu.add(primerPanel(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 2; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 2; 
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(10, 10, 10, 10);
		panelPerfilUsu.add(segundoPanel(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 2; 
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);
		panelPerfilUsu.add(tercerPanel(), constraints);
		
		return panelPerfilUsu;
	}
	
	private JPanel panelCompatibilidadUsuarios(){
		JPanel panelCompUsu = new JPanel();
		panelCompUsu.setLayout(new GridBagLayout());
		
		compInfo = new JLabel("Tu nivel de compatibilidad es de:");
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		
		constraints.anchor = GridBagConstraints.CENTER;
		panelCompUsu.add(compInfo, constraints);
		
		compInfoPorc = new JLabel("%");
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		panelCompUsu.add(compInfoPorc, constraints);
		
		compInfoComunes = new JLabel("Intereses comunes:");
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		panelCompUsu.add(compInfoComunes, constraints);
		this.listAficCompat = new JList<String>();
		JScrollPane scrollPane = new JScrollPane(this.listAficCompat);
		listModelCompat = new DefaultListModel<String>();

		
		listAficCompat.setModel(listModelCompat);
		scrollPane.setPreferredSize(new Dimension(600,350));
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 4;
		constraints.weightx =0.1;
		constraints.weighty = 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		panelCompUsu.add(scrollPane, constraints);
		
		
		
		return panelCompUsu;
	}
	private JPanel primerPanel(){
		JPanel panel1 = new JPanel(new GridBagLayout());
		

		JPanel panelIzquierdo = new JPanel(new GridBagLayout());
		JPanel panelDcho = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		panel1.add(panelIzquierdo, constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		panel1.add(panelDcho, constraints);
		
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 4;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;

		
		this.img = new JLabel();
		img.setPreferredSize(new Dimension(100, 100));
		ImageIcon icono = null;
		java.net.URL url_icono = null;
		url_icono = getClass().getResource("twitter-for-ios-app-thumbnail.jpg");
		ImageIcon fot = new ImageIcon(url_icono);
		icono = new ImageIcon(fot.getImage().getScaledInstance(img.getPreferredSize().width, img.getPreferredSize().height, Image.SCALE_DEFAULT));
		this.img.setIcon(icono);
		panelIzquierdo.add(this.img, constraints);
		
		this.nombreL = new JLabel();
		constraints = new GridBagConstraints();
		constraints.gridx = 4; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		panelIzquierdo.add(nombreL, constraints);	
		
		this.edadL = new JLabel();
		constraints = new GridBagConstraints();
		constraints.gridx = 4; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		panelIzquierdo.add(edadL, constraints);
		
		return panel1;
	}
	
	private JPanel segundoPanel(){ //Controlar que no se vaya de tamaño
		JPanel panel2 = new JPanel(new GridBagLayout());
		JLabel nombreL = new JLabel("Descripción:");
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		panel2.add(nombreL, constraints);
		
		this.descripT = new JTextArea(8, 70);
		this.descripT.setEditable(false);
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel2.add(descripT, constraints);
		
		return panel2;
	}
	
	private JPanel tercerPanel(){
		JPanel panel3 = new JPanel(new GridBagLayout());
		
		JLabel nombreL = new JLabel("Aficiones:");
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		panel3.add(nombreL, constraints);
		
		
		this.listAfic = new JList<String>();
		JScrollPane scrollPane = new JScrollPane(this.listAfic);
		listModel = new DefaultListModel<String>();

		
		listAfic.setModel(listModel);
		//scrollPane.setPreferredSize(new Dimension(600,100));
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 4;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		
		panel3.add(scrollPane, constraints);
		
		JLabel sexoL = new JLabel("Sexo: ");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		panel3.add(sexoL, constraints);
		
		this.sexoL2 = new JLabel();
		
		constraints = new GridBagConstraints();
		
		constraints.gridx = 1; 
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		panel3.add(sexoL2, constraints);
	
		JLabel buscaL = new JLabel("Busca: ");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		panel3.add(buscaL, constraints);
		
		buscaL2 = new JLabel();
		constraints = new GridBagConstraints();
		
		constraints.gridx = 1; 
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.WEST;
		panel3.add(buscaL2, constraints);
		
		return panel3;
	}
	
	private JPanel cuartoPanel(){
		JPanel panel4 = new JPanel(new GridBagLayout());
		distancia = new JLabel("distancia: ");
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 8;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		constraints.anchor = GridBagConstraints.WEST;
		
		panel4.add(distancia, constraints);	
		
		amChBtn = new JButton("Enviar peticion de amistad");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 8; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;

		
		panel4.add(amChBtn, constraints);	
		
		
		cancelBtn = new JButton("Cancelar");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 9; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
	

		constraints.anchor = GridBagConstraints.EAST;
		panel4.add(cancelBtn, constraints);		
		
		
		return panel4;
	}
	
	private JPanel panelChat(){
		JPanel panelChat = new JPanel();
		panelChat.setLayout(new BorderLayout());
		
		this.norteChat = this.panelNorteChat();
		panelChat.add(this.norteChat, BorderLayout.NORTH);
		
		this.surChat = this.panelSurChat();
		panelChat.add(this.surChat, BorderLayout.SOUTH);
		
		return panelChat;
	}
	
	private JPanel panelNorteChat(){
		JPanel panel = new JPanel();
		
		this.listaMensajes = new JList<String>();
		this.paneMensajes = new JScrollPane(this.listaMensajes);
		this.modelMensajes = new DefaultListModel<String>();
		this.listaMensajes.setModel(this.modelMensajes);
		this.paneMensajes.setPreferredSize(new Dimension(770, 430));
		panel.add(this.paneMensajes);
		
		return panel;
	}
	
	private JPanel panelSurChat(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints cons;
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 5, 20, 0);
		cons.gridx = 0; //columna
		cons.gridy = 0; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.enviarM = new JLabel("Enviar mensaje");
		panel.add(this.enviarM, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 120, 20, 5);
		cons.gridx = 0; //columna
		cons.gridy = 0; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 150;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.enviarMensaje = new JTextArea();
		panel.add(this.enviarMensaje, cons);
		
		return panel;
	}
	
	private void initChat(){
		List<MTipoTexto> lista = this.controlador.cargarMensajes(usu.getCorreo());
		
		for(MTipoTexto mt: lista){
			String cabecera = new String("[" + mt.getFechaYHora() + "] " + mt.getEmisor().getNombre() + " escribió: ");
			modelMensajes.addElement(cabecera);
			modelMensajes.addElement(mt.getTexto());
		}
		
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
}
