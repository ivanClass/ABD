package abd.p1.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import abd.p1.controller.Controlador;
import abd.p1.model.Aficion;
import abd.p1.model.Generos;
import abd.p1.model.Usuario;

public class EditarUsuario extends JPanel implements Observer  {
	private Controlador controlador;
	private JLabel nombreL;
	private JLabel img;
	private JLabel edadL;
	private JTextArea descripT;
	private JList<String> listAfic;
	private DefaultListModel<String> listModel;
	private Usuario usu;
	private JDateChooser dateChooser_1;
	private JLabel sexoL2;
	private JLabel buscaL2;
	private AbstractButton cambiarBtn;
	private JButton cambiarAvaBtn;
	private JButton addAficBtn;
	private JButton rmAficBtn;
	private JButton edAficBtn;
	private JButton chSexBtn;
	private JButton chPrefBtn;
	private JButton chPswBtn;
	private JButton svChBtn;
	private MainFrame ventana;
	private JButton cancelBtn;
	
	public EditarUsuario(Controlador control, Usuario u, MainFrame ventana){
		this.controlador = control;
		this.usu = u;
		this.ventana = ventana;
		this.controlador.setUsuario(u);
		this.controlador.addObservador(this);
		this.initGUI();
		this.confEventos();
		this.iniDatos();
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
	}
	
	private void initGUI() {
		this.setLayout(new GridBagLayout());
		
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
		this.add(primerPanel(), constraints);
		
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
		this.add(segundoPanel(), constraints);
		
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
		this.add(tercerPanel(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);
		this.add(cuartoPanel(), constraints);		
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
		
		
		cambiarBtn = new JButton("Cambiar nombre");
		constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.BOTH;
		panelDcho.add(cambiarBtn, constraints);
		
		constraints = new GridBagConstraints();
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		String fechaact = dateFormat.format(cal.getTime());
		this.dateChooser_1 = new JDateChooser();
		this.dateChooser_1.setFont(new Font("Dialog", Font.BOLD, 12));
		this.dateChooser_1.setDateFormatString("dd-MM-yyyy");
		try {
			this.dateChooser_1.setDate(dateFormat.parse(fechaact));
			this.dateChooser_1.setMaxSelectableDate(dateFormat.parse(fechaact));
		} catch (ParseException e) {
		}
		
		constraints.gridx = 0; 
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets = (new Insets(50, 0, 0, 0));
		constraints.fill = GridBagConstraints.BOTH;
		panelDcho.add(this.dateChooser_1, constraints);
		
		cambiarAvaBtn = new JButton("Cambiar avatar");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.insets = (new Insets(10, 0, 0, 0));
		constraints.fill = GridBagConstraints.BOTH;

		panelDcho.add(cambiarAvaBtn, constraints);
		
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
		
		this.descripT = new JTextArea();
		this.descripT.setPreferredSize(new Dimension(300, 100));
		this.descripT.setAutoscrolls(true);
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		constraints.fill = GridBagConstraints.BOTH;
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
		scrollPane.setPreferredSize(new Dimension(600,100));
		constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.gridheight = 4;
		constraints.fill = GridBagConstraints.BOTH;
		panel3.add(scrollPane, constraints);
		
		addAficBtn = new JButton("Añadir afición");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 5; 
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		panel3.add(addAficBtn, constraints);	
		
		rmAficBtn = new JButton("Eliminar afición");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 5; 
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;

		constraints.fill = GridBagConstraints.BOTH;
		panel3.add(rmAficBtn, constraints);	
		
		
		edAficBtn = new JButton("Editar afición");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 5; 
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;

		constraints.fill = GridBagConstraints.BOTH;
		panel3.add(edAficBtn, constraints);
		
		
		
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

		chSexBtn = new JButton("Cambiar sexo");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 5; 
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		constraints.fill = GridBagConstraints.BOTH;
		panel3.add(chSexBtn, constraints);

		chPrefBtn = new JButton("Cambiar preferencia");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 5; 
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;

		constraints.fill = GridBagConstraints.BOTH;
		panel3.add(chPrefBtn, constraints);
		
		return panel3;
	}
	
	private JPanel cuartoPanel(){
		JPanel panel4 = new JPanel(new GridBagLayout());
		chPswBtn = new JButton("Cambiar contrasena");
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 8;
		constraints.gridheight = 1;
		constraints.weightx = 0.1;
		constraints.weighty = 0.1;
		constraints.anchor = GridBagConstraints.WEST;
		
		panel4.add(chPswBtn, constraints);	
		
		svChBtn = new JButton("Guardar cambios");
		constraints = new GridBagConstraints();
		
		constraints.gridx = 8; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.EAST;

		
		panel4.add(svChBtn, constraints);	
		
		
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
	
	private void confEventos(){
		cambiarBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object result = JOptionPane.showInputDialog("Nombre:");
				if(result != null)
					controlador.setNombreUsu(result.toString());
			}
		});
		
		cambiarAvaBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showDialog(null,"Cambiar avatar");
				File f = fc.getSelectedFile();
				
				if(f != null){
					ImageIcon ic = new ImageIcon(f.getAbsolutePath());
					BufferedImage bi = new BufferedImage(
					 ic.getIconWidth(), ic.getIconHeight(),
					 BufferedImage.TYPE_INT_RGB
					 );
					Graphics g = bi.getGraphics();
					ic.paintIcon(null, g, 0, 0);
					g.dispose();
					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					try {
						ImageIO.write(bi, "png", byteStream);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					byte[] array = byteStream.toByteArray();
					controlador.setImgUsu(array);
				}
			}
		});
		
		this.dateChooser_1.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
	        		controlador.setFechaUsu(dateChooser_1.getDate());
	        		
				}
				
			}
		});
		
		addAficBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object result = JOptionPane.showInputDialog("Aficion:");
				
				controlador.addAficinUsu((String) result);		
			}
		});
		
		rmAficBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listAfic.getSelectedIndex() != -1){
					controlador.rmAficionUsu(listAfic.getSelectedIndex());
					listModel.remove(listAfic.getSelectedIndex());
				};
				
			}
		});
		
		edAficBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(listAfic.getSelectedIndex() != -1){
					Object result = JOptionPane.showInputDialog("Aficion:");
					if(result != null){
						controlador.editAficionUsu((String) result, listAfic.getSelectedIndex());
						listModel.set(listAfic.getSelectedIndex(), result.toString());
					}
				};	
			}
		});
		
		chSexBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Object selection = JOptionPane.showInputDialog(null, "Sexo",
				        "Cambiar sexo", JOptionPane.QUESTION_MESSAGE, null, Generos.values(), Generos.HOMBRE);
				
				if(selection != null)
					controlador.setGeneroUsu((Generos)selection);
			}
		});
		
		chPrefBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selection = JOptionPane.showInputDialog(null, "Sexo",
				        "Cambiar sexo", JOptionPane.QUESTION_MESSAGE, null, Generos.values(), Generos.HOMBRE);
				
				if(selection != null)
					controlador.setInteresUsu((Generos)selection);
			}
		});
		
		chPswBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					      JTextField pswF = new JPasswordField(20);
		
					      JPanel myPanel = new JPanel(new GridBagLayout());
					      GridBagConstraints constraints = new GridBagConstraints();
							
					      constraints.gridx = 0; 
					      constraints.gridy = 0;
					      constraints.gridwidth = 1;
					      constraints.gridheight = 1;
					      constraints.weightx = 0.1;
					      constraints.weighty = 0.1;
					      constraints.fill = GridBagConstraints.BOTH;
					      myPanel.add(new JLabel("Nueva contrasena: "), constraints);

					      constraints = new GridBagConstraints();
					      constraints.gridx = 1; 
					      constraints.gridy = 0;
					      constraints.gridwidth = 1;
					      constraints.gridheight = 1;
					      myPanel.add(pswF, constraints);
					     
					      int result = JOptionPane.showConfirmDialog(null, myPanel, 
					               "Cambiar psw", JOptionPane.OK_CANCEL_OPTION);

					      if(pswF.getText().length() != 0){
					    	 controlador.setPswUsu(pswF.getText());
					      }
						
					}
		});
		
		svChBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String s = descripT.getText();
				controlador.setDescripUsu(s);
				controlador.addOrUpdateUsuario(usu);
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
	}
	
	private void remove(){
		this.ventana.remove(this);
	}


	
	@Override
	public void notifyNameCh(String s) {
		nombreL.setText(s);
		
	}


	@Override
	public void notifyEdadCh(int n) {
		Integer i = n;
		edadL.setText(i.toString());
		
	}


	@Override
	public void notifySexCh(String g) {
		sexoL2.setText(g);
		
	}


	@Override
	public void notifyAddAf(String s) {
		listModel.addElement(s);
	}


	@Override
	public void notifyPrefCh(String p) {
		buscaL2.setText(p);
	}


	@Override
	public void notifyAvaCh(byte[] imagen) {
		ImageIcon icono = null;
		ImageIcon fot = new ImageIcon(imagen);
		icono = new ImageIcon(fot.getImage().getScaledInstance(img.getPreferredSize().width, img.getPreferredSize().height, Image.SCALE_DEFAULT));
		this.img.setIcon(icono);		
	}
}
