package abd.p1.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import abd.p1.controller.Controlador;

public class MainFrame extends JFrame{
	private Container contentPane;
	private Controlador controlador;
	private static Stack<JPanel> paneles;
	
	public MainFrame(Controlador c){
		super("AshleyMadison");
		paneles = new Stack<JPanel>();
		this.controlador = c;
		initGUI();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });

	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		this.contentPane.setLayout(new GridBagLayout());
		this.setResizable(true);
		this.setContentPane(this.contentPane);
		this.setResizable(false);
		//this.setMinimumSize(new Dimension(400, 300));
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints;
		
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		this.contentPane.add(new Sesion(this.controlador, this), constraints);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public static void metePila(JPanel panel){
		paneles.push(panel);
	}
	
	public static JPanel sacaPila(){
		return paneles.pop();
		
	}
	
    private void close(){
    	controlador.finish();
    }    
	
	
	
}
