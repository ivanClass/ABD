package abd.p1.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import abd.p1.model.Pregunta;
import abd.p1.model.PreguntaResumen;

public class PreguntaCellRender extends ElementoListaPregunta implements ListCellRenderer<PreguntaResumen>{

	@Override
	public Component getListCellRendererComponent(
			JList<? extends PreguntaResumen> list, PreguntaResumen value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		this.setTituloPreg(value.getTituloPregunta());
		this.setNumOpciones(value.getNumOpcionesPregunta());
		
		if(isSelected){
			this.setBackground(Color.cyan);
		}
		else{
			this.setBackground(Color.WHITE);
		}
		
		return this;
	}
}