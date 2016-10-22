package abd.p1.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import abd.p1.controller.Controlador;
import abd.p1.model.MTipoPetAmistad;
import abd.p1.model.MTipoPregunta;
import abd.p1.model.MTipoTexto;
import abd.p1.model.Mensaje;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.model.PreguntaResumen;
import abd.p1.model.Usuario;

public class Principal extends JPanel implements Observer{
	private JPanel usuarios;
	private JPanel preguntas;
	private JPanel mensNoLeidos;
	
	//paneles usuario
	private JPanel norte;
	private JPanel centro;
	private JPanel sur;
	
	private JTabbedPane panelPestanas;
	
	//pestaña usuarios
	private JList listaUsuarios;
	private JScrollPane scrollPaneUsuarios;
	private DefaultListModel<Usuario> listModel;
	private JLabel labelMostrar;
	private JLabel labelFiltrar;
	private JTextField textFiltrar;
	private JButton botonModificar;
	private JButton botonVer;
	private JCheckBox checkFiltrar;
	private JCheckBox checkMostrar;
	
	//pestaña preguntas
	private JPanel nortePreg;
	private JPanel surPreg;
	
	private JList listaPreguntas;
	private JLabel labelTitulo;
	private JScrollPane scrollPanelPreguntas;
	private DefaultListModel<PreguntaResumen> listModelPreguntas;
	private JButton botonResponder;
	private JButton botonPregAleat;
	
	//pestaña mensajes no leidos
	private JPanel norteMensajes;
	private JPanel surMensajes;
	
	private JList<String> listaMensajes;
	private JScrollPane paneMensajes;
	private DefaultListModel<String> modelMensajes;
	
	private JButton botonMarcarNoLeidos;
	
	
	private Controlador controlador;



	private MainFrame ventana;
	
	public Principal(Controlador contr, MainFrame ventana, Usuario aux){
		this.controlador = contr;
		this.controlador.setUsuario(aux);
		controlador.coordenadasRandomUsu();
		controlador.updateUsuario();
		this.ventana = ventana;
		
		initGUI();
		initListaMensajes();
		confEventos();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());

		this.panelPestanas = new JTabbedPane();
		this.panelPestanas.addTab("Usuarios", this.panelUsuarios());
		this.panelPestanas.addTab("Preguntas", this.panelPreguntas());
		this.panelPestanas.addTab("Mensajes no leídos", this.panelMensajesNoLeidos());
		this.add(this.panelPestanas, BorderLayout.CENTER);

	
		
		
		//this.setMinimumSize(new Dimension(350, 375));
		
	}
	
	private JPanel panelUsuarios(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		this.norte = this.panelNorte();
		panel.add(this.norte, BorderLayout.NORTH);
		
		this.centro = this.panelCentro();
		panel.add(this.centro, BorderLayout.CENTER);
		
		this.sur = this.panelSur();
		panel.add(this.sur, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel panelPreguntas(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		this.nortePreg = panelNortePreg();
		panel.add(this.nortePreg, BorderLayout.NORTH);
		
		this.surPreg = panelSurPreg();
		panel.add(this.surPreg, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel panelMensajesNoLeidos(){
		JPanel panelChat = new JPanel();
		panelChat.setLayout(new BorderLayout());
		
		this.norteMensajes = this.panelNorteMensajes();
		panelChat.add(this.norteMensajes, BorderLayout.NORTH);
		
		this.surMensajes = this.panelSurMensajes();
		panelChat.add(this.surMensajes, BorderLayout.SOUTH);
		
		return panelChat;
	}
	
	private JPanel panelNorte(){
		JPanel panel = new JPanel();
		
		this.listaUsuarios = new JList<ElementoListaUsuario>();
		this.scrollPaneUsuarios = new JScrollPane(this.listaUsuarios);
		this.listModel = new DefaultListModel<Usuario>();
		this.listaUsuarios.setModel(this.listModel);
		this.listaUsuarios.setCellRenderer(new UsuarioCellRender());
		this.scrollPaneUsuarios.setPreferredSize(new Dimension(300, 200));
		panel.add(this.scrollPaneUsuarios);
		
		return panel;
	}
	
	private JPanel panelCentro(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints cons;
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 11, 0, 0);
		cons.gridx = 0; //columna
		cons.gridy = 0; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.checkFiltrar = new JCheckBox();
		panel.add(this.checkFiltrar, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 11, 0, 0);
		cons.gridx = 0; //columna
		cons.gridy = 1; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.checkMostrar = new JCheckBox();
		panel.add(this.checkMostrar, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 1; //columna
		cons.gridy = 0; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.labelFiltrar = new JLabel("Filtrar por nombre: ");
		panel.add(this.labelFiltrar, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridx = 1; //columna
		cons.gridy = 1; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.labelMostrar = new JLabel("Mostrar sólo amigos: ");
		panel.add(this.labelMostrar, cons);
		
		cons = new GridBagConstraints();
		cons.insets = new Insets(0, 0, 0, 15);
		cons.gridx = 2; //columna
		cons.gridy = 0; //fila
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.weightx = 10;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTHWEST;
		this.textFiltrar = new JTextField();
		panel.add(this.textFiltrar, cons);
		
		
		
		return panel;
	}
	
	private JPanel panelSur(){
		JPanel panel = new JPanel();
		
		this.botonModificar = new JButton("Modificar mi perfil");
		panel.add(this.botonModificar);
		
		this.botonVer = new JButton("Ver perfil seleccionado");
		panel.add(this.botonVer);
		
		return panel;
	}
	
	private JPanel panelNortePreg(){
		JPanel panel = new JPanel();
	
		this.listaPreguntas = new JList<ElementoListaPregunta>();
		this.scrollPanelPreguntas = new JScrollPane(this.listaPreguntas);
		this.listModelPreguntas = new DefaultListModel<PreguntaResumen>();
		this.listaPreguntas.setModel(this.listModelPreguntas);
		this.listaPreguntas.setCellRenderer(new PreguntaCellRender());
		this.scrollPanelPreguntas.setPreferredSize(new Dimension(300, 200));
		panel.add(this.scrollPanelPreguntas);
		
		this.cargarPanelPreguntaLista(this.controlador.cargar20PreguntasMasValoradas());
		
		
		return panel;
	}
	
	private JPanel panelSurPreg(){
		JPanel panel = new JPanel();
		
		this.botonResponder = new JButton("Responder");
		panel.add(this.botonResponder);
		this.botonPregAleat = new JButton("Pregunta aleatoria");
		panel.add(this.botonPregAleat);
		
		return panel;
	}
	
	private JPanel panelNorteMensajes(){
		JPanel panel = new JPanel();
		
		this.listaMensajes = new JList<String>();
		this.paneMensajes = new JScrollPane(this.listaMensajes);
		this.modelMensajes = new DefaultListModel<String>();
		this.listaMensajes.setModel(this.modelMensajes);
		this.paneMensajes.setPreferredSize(new Dimension(300, 200));
		panel.add(this.paneMensajes);
		
		return panel;
	}
	
	private JPanel panelSurMensajes(){
		JPanel panel = new JPanel();
		
		this.botonMarcarNoLeidos = new JButton("Marcar todos como no leídos");
		panel.add(this.botonMarcarNoLeidos);
		
		return panel;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	private void confEventos(){
		this.checkFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.botonMarcarNoLeidos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.marcarLeidos();
				
			}
		});
		
		this.checkMostrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List lista = controlador.cargarAmigosOrdenDistancia();
				cargarPanelUsuarioLista(lista);
			}
		});
		
		/*
		this.textFiltrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List lista = controlador.cargarListasUsuarioFiltro(textFiltrar.getText());
				cargarPanelUsuarioLista(lista);
			}
		});
		*/
		this.textFiltrar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {

				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				List lista;
				String filtro = null;
				if(checkFiltrar.isSelected() && !checkMostrar.isSelected()){
					 filtro = textFiltrar.getText();
					 lista = controlador.cargar20UsuariosMasCercanos(filtro);
				}
				else if(checkFiltrar.isSelected() && checkMostrar.isSelected()){
					 filtro = textFiltrar.getText();
					 lista = controlador.cargarAmigosOrdenDistancia(filtro);
				}
				else{
					 lista = controlador.cargar20UsuariosMasCercanos(filtro);

				}
				
				cargarPanelUsuarioLista(lista);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		botonVer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = listaUsuarios.getSelectedIndex();
				if(i != -1){
					Usuario u = controlador.dameUsuario(listModel.get(i).getCorreo());
					meteAlaPila();
					remove();
					PerfilUsuario perfilUsuario = new PerfilUsuario(controlador, u, ventana);
					ventana.add(perfilUsuario);
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				}
				
			}
		});
		
		botonModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				meteAlaPila();
				remove();
				EditarUsuario eu = new EditarUsuario(controlador, controlador.getUsuario(), ventana);
				ventana.add(eu);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				
			}
		});
		
		this.botonResponder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = listaPreguntas.getSelectedIndex();
				
				Pregunta p = controlador.damePregunta(listModelPreguntas.get(i).getIdPregunta());
				
				meteAlaPila();
				remove();
				ResponderPregunta responderPregunta = new ResponderPregunta(controlador, ventana, p);
				ventana.add(responderPregunta);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				
			}
		});
		
		this.botonPregAleat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Pregunta p = controlador.damePreguntaAleatoria();
				
				if(p == null){
					//MOSTRAR QUE NO HAY PREGUNTAS EN LA BBDD
				}
				else{
					meteAlaPila();
					remove();
					ResponderPregunta responderPregunta = new ResponderPregunta(controlador, ventana, p);
					ventana.add(responderPregunta);
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				}
				
			}
		});
	}
	
	private void cargarPanelUsuarioLista(List listaUsu){
		this.listModel.clear();
		
		if(listaUsu != null){
			Iterator it = listaUsu.iterator();
			while(it.hasNext()) {
				Object[] element = (Object[]) it.next();
				Usuario us = new Usuario();
				us.setImagenPerfil((byte[]) element[0]);
				us.setNombre((String) element[1]);
				us.setFechaNacimiento((Date) element[2]);
				us.setCorreo((String) element[3]);
				this.listModel.addElement(us);	
			}
		}
	}
	
	private void cargarPanelPreguntaLista(List listaPreg){
		Iterator it = listaPreg.iterator();
		while(it.hasNext()){
			Object[] element = (Object[]) it.next();
			PreguntaResumen preg = new PreguntaResumen();
			preg.setTituloPregunta((String) element[0]);
			preg.setIdPregunta((Integer) element[1]);
			preg.setNumOpcionesPregunta((int)(long)element[2]);
			this.listModelPreguntas.addElement(preg);
		}
	}
	
	private void initListaMensajes(){
		List<Mensaje> lista = this.controlador.cargarTodosLosMensajesNoLeidos();
		
		if(lista != null){
		
			List<String> l = null;
			for(Mensaje mt: lista){
				if( mt.getClass().getName().equals("abd.p1.model.MTipoTexto")){
					MTipoTexto m = (MTipoTexto) mt;
					l = m.cuerpoMensaje(m);
				}
				if( mt.getClass().getName().equals("abd.p1.model.MTipoPregunta")){
					MTipoPregunta m =  (MTipoPregunta) mt;
					l = m.cuerpoMensaje(m);
				}
				if( mt.getClass().getName().equals("abd.p1.model.abd.p1.model.MTipoPetAmistad")){
					MTipoPetAmistad m = (MTipoPetAmistad) mt;
					l = m.cuerpoMensaje(m);
				}
				for(String s: l){
					modelMensajes.addElement(s);
				}
			}
		}
		
	}
	
	private void meteAlaPila(){
		this.ventana.metePila(this);
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
	
	public void refrescarPreguntas(){
		this.listModelPreguntas.clear();
		this.cargarPanelPreguntaLista(this.controlador.cargar20PreguntasMasValoradas());
	}
	
}
