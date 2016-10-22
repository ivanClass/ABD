package abd.p1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import abd.p1.controller.Controlador;
import abd.p1.model.Pregunta;
import abd.p1.model.Usuario;

public class InvitarResponderPregunta extends JPanel{
	
	private JPanel panelNorte;
	private JPanel panelCentro;
	private JPanel panelSur;
	
	private JList listaUsuarios;
	private JScrollPane scrollPaneUsuarios;
	private DefaultListModel<Usuario> listModel;
	private JLabel labelMostrar;
	private JLabel labelFiltrar;
	private JTextField textFiltrar;
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JCheckBox checkFiltrar;
	private JCheckBox checkMostrar;
	
	private Controlador controlador;
	private Pregunta pregunta;
	private MainFrame ventana;
	
	public InvitarResponderPregunta(Controlador contr, MainFrame ventana, Pregunta preg){
		this.controlador = contr;
		controlador.coordenadasRandomUsu();
		controlador.updateUsuario();
		this.ventana = ventana;
		this.pregunta = preg;
		
		initGUI();
		confEventos();
	}
	
	private void initGUI(){
		this.setLayout(new BorderLayout());

		this.panelNorte = this.panelNorte();
		this.add(this.panelNorte, BorderLayout.NORTH);
		
		this.panelCentro = this.panelCentro();
		this.add(this.panelCentro, BorderLayout.CENTER);
		
		this.panelSur = this.panelSur();
		this.add(this.panelSur, BorderLayout.SOUTH);
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
		
		this.botonAceptar = new JButton("Aceptar");
		panel.add(this.botonAceptar);
		
		this.botonCancelar = new JButton("Cancelar");
		panel.add(this.botonCancelar);
		
		return panel;
	}
	
	private void confEventos(){
		this.textFiltrar.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				List lista;
				String filtro = null;
				if(checkFiltrar.isSelected()){
					 filtro = textFiltrar.getText();
					 lista = controlador.cargar20UsuariosMasCercanos(filtro);
				}
				else{
					 lista = controlador.cargar20UsuariosMasCercanos(filtro);

				}
				
				cargarPanelUsuarioLista(lista);
				
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = listaUsuarios.getSelectedIndex();
				if(i != -1){
					Usuario destino = controlador.dameUsuario(listModel.get(i).getCorreo());
					controlador.invitarPregunta(pregunta, destino);
					remove();
					ventana.add(MainFrame.sacaPila());
					revalidate();
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				}
				
			}
		});
		
		this.botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
				ventana.add(MainFrame.sacaPila());
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				
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
	
	private void remove(){
		this.ventana.remove(this);
	}

}
