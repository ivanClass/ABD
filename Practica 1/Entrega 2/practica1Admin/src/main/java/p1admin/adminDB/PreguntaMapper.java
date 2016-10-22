package p1admin.adminDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import p1admin.model.Opcion;
import p1admin.model.Pregunta;

public class PreguntaMapper extends AbstractMapper<Pregunta, Integer>{

	public PreguntaMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return "preguntas";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[] {"IdPregunta", "Descripcion"};
	}

	@Override
	protected String[] getKeyColumnName() {
		return new String[] {"IdPregunta"};
	}

	@Override
	protected Object[] getValues(Pregunta pregunta) {
		return new Object[] {pregunta.getId(), pregunta.getEnunciado()};
	}

	@Override
	protected Object[] getKeyValues(Pregunta objeto) {
		return new Object[] {objeto.getId()};
	}
	

	public List<Pregunta> findQuestionsContaining(String text){
		List<Pregunta> lista =  new ArrayList<>();
		Pregunta pregunta = null;
		Opcion opcion = null;
		int idPregunta = -5;
		
		String sql = "SELECT * FROM Preguntas LEFT JOIN Respuestas ON Preguntas.IdPregunta = Respuestas.IdPregunta WHERE Preguntas.Descripcion LIKE '%" + text + "%' ORDER BY Respuestas.IdPregunta, Respuestas.numOrden";

		try(Connection con = ds.getConnection();
				
			PreparedStatement pst = con.prepareStatement(sql)){
			
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("IdPregunta") != idPregunta){
					pregunta = new Pregunta();
					pregunta.setId(rs.getInt("IdPregunta"));
					pregunta.setEnunciado(rs.getString("Descripcion"));
					lista.add(pregunta);
				}
				
				opcion = new Opcion();

				opcion.setIdOpcion(rs.getInt("IdRespuesta"));
				opcion.setNumeroOrden(rs.getInt("numOrden"));
				opcion.setTexto(rs.getString(5));
				opcion.setPreguntaMadre(pregunta);
				if(opcion.getTexto() != null)
					pregunta.addOpcion(opcion);
				
				
				idPregunta = pregunta.getId();
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return lista;
	}
	
	public List<Pregunta> getAllQuestions(){
		List<Pregunta> lista =  new ArrayList<>();
		Pregunta pregunta = null;
		Opcion opcion = null;
		int idPregunta = -5;
		
		String sql = "SELECT * FROM Preguntas LEFT JOIN Respuestas ON Preguntas.IdPregunta = Respuestas.IdPregunta ORDER BY Respuestas.IdPregunta, Respuestas.numOrden";

		try(Connection con = ds.getConnection();
				
			PreparedStatement pst = con.prepareStatement(sql)){
			
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				if(rs.getInt("IdPregunta") != idPregunta){
					pregunta = new Pregunta();
					pregunta.setId(rs.getInt("IdPregunta"));
					pregunta.setEnunciado(rs.getString("Descripcion"));
					lista.add(pregunta);
				}
				
				opcion = new Opcion();

				opcion.setIdOpcion(rs.getInt("IdRespuesta"));
				opcion.setNumeroOrden(rs.getInt("numOrden"));
				opcion.setTexto(rs.getString(5));
				opcion.setPreguntaMadre(pregunta);
				if(opcion.getTexto() != null)
					pregunta.addOpcion(opcion);
				
				
				idPregunta = pregunta.getId();
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return lista;	
	}
	
}
