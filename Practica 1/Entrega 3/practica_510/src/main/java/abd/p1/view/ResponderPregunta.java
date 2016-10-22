package abd.p1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import abd.p1.controller.Controlador;
import abd.p1.model.Contesta;
import abd.p1.model.Opcion;
import abd.p1.model.Pregunta;
import abd.p1.model.PreguntaResumen;
import abd.p1.model.Usuario;

public class ResponderPregunta extends JPanel{
	
	private JPanel panelNorte;
	private JPanel panelCentro;
	private JPanel panelSur;
	
	private JLabel labelEnunciadoPreg;
	private JList listaOpciones;
	private JScrollPane scrollPaneOpciones;
	private DefaultListModel<Opcion> listModel;
	
	private JLabel labelRelevancia;
	private JSlider sliderRelevancia;
	
	private JButton botonResponder;
	private JButton botonInvitarAmigo;
	
	private Controlador controlador;
	private MainFrame ventana;
	private Pregunta pregunta;
	
	
	
	public ResponderPregunta(Controlador contr, MainFrame ventana, Pregunta preg){
		this.controlador = contr;
		this.ventana = ventana;
		this.pregunta = preg;
		
		this.initGUI();
		this.confEventos();
	}


	private void initGUI() {
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
		panel.setLayout(new BorderLayout());
		
		this.labelEnunciadoPreg = new JLabel(this.pregunta.getEnunciado());
		Border marginT = new EmptyBorder(3, 3, 8, 3);
		this.labelEnunciadoPreg.setBorder(marginT);
		this.labelEnunciadoPreg.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(this.labelEnunciadoPreg, BorderLayout.NORTH);
		
		this.listaOpciones = new JList();
		this.scrollPaneOpciones = new JScrollPane(this.listaOpciones);
		this.listModel = new DefaultListModel<Opcion>();
		this.listaOpciones.setModel(this.listModel);
		this.scrollPaneOpciones.setPreferredSize(new Dimension(300, 200));

		panel.add(this.scrollPaneOpciones, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel panelCentro(){
		JPanel panel = new JPanel();
		
		this.labelRelevancia = new JLabel("Relevancia: ");
		panel.add(this.labelRelevancia);
		
		this.sliderRelevancia = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		this.sliderRelevancia.setPaintTicks(true);
		this.sliderRelevancia.setMajorTickSpacing(5);
		this.sliderRelevancia.setMinorTickSpacing(1);
		this.sliderRelevancia.setPaintLabels(true);
		
		panel.add(this.sliderRelevancia);
		
		this.cargarPanelOpciones();
		
		return panel;
	}
	
	private JPanel panelSur(){
		JPanel panel = new JPanel();
		
		this.botonResponder = new JButton("Responder");
		panel.add(this.botonResponder);
		this.botonInvitarAmigo = new JButton("Invitar a un amigo");
		panel.add(this.botonInvitarAmigo);
		
		return panel;
	}
	

	private void confEventos() {

		this.botonResponder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listaOpciones.isSelectionEmpty()){
					JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna opción");
				}
				else{
					controlador.respuestaPregunta(pregunta, (Opcion) listaOpciones.getSelectedValue(), sliderRelevancia.getValue());
					remove();
					Principal p;
					p = (Principal) MainFrame.sacaPila();
					p.refrescarPreguntas();
					ventana.add(p);
					revalidate();
					
					ventana.pack();
					ventana.setLocationRelativeTo(null);
				}
				
			}
		});
		
		this.botonInvitarAmigo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				meteAlaPila();
				remove();
				InvitarResponderPregunta invitarPregunta = new InvitarResponderPregunta(controlador, ventana, pregunta);
				ventana.add(invitarPregunta);
				revalidate();
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				
			}
		});
		
	}
	
	private void cargarPanelOpciones(){
		Iterator it = this.pregunta.getOpciones().iterator();
		while(it.hasNext()){
			Opcion element = (Opcion) it.next();
			this.listModel.addElement(element);
		}
		
	}
	
	private void remove(){
		this.ventana.remove(this);
	}
	
	private void meteAlaPila(){
		this.ventana.metePila(this);
	}

}
