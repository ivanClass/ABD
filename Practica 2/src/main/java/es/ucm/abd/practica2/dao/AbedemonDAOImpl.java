package es.ucm.abd.practica2.dao;

import es.ucm.abd.practica2.model.Abedemon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Element;

/**
 * Implementación concreta del DAO que hace llamadas a eXist.
 * 
 * @author Manuel Montenegro (mmontene@ucm.es)
 */
public class AbedemonDAOImpl implements AbedemonDAO {

    private final XQDataSource ds;

    public AbedemonDAOImpl(XQDataSource ds) {
        this.ds = ds;
    }

    
    /**
     * Obtiene los tipos de especies disponibles en la BD.
     * 
     * @return Lista de tipos de especies (sin duplicados)
     */
    @Override
    public List<String> getTypes() {
    	InputStream is = getClass().getResourceAsStream("Abedemon1.xquery");
    	List<String> lista = new ArrayList<String>();
    	try {
			XQConnection con = ds.getConnection();
			XQPreparedExpression exp = con.prepareExpression(is);
			XQResultSequence rs = exp.executeQuery();
			while (rs.next()) {
				lista.add(rs.getItemAsString(null));
			}

		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return lista;
    }

    /**
     * Obtiene las especies de abedemon de un determinado.
     * 
     * @param type Tipo a buscar
     * @return Especies encontradas del tipo pasado como parámetro.
     */
    @Override
    public List<Abedemon> getAbedemonsOf(String type) {
    	InputStream is = getClass().getResourceAsStream("Abedemon2.xquery");
    	List<Abedemon> lista = new ArrayList<Abedemon>();
    	Element result = null;
    	String id;
    	String nombre;
    	int numPoderes;

    	try {
			XQConnection con = ds.getConnection();
			XQPreparedExpression exp = con.prepareExpression(is);
			exp.bindString(new QName("tipo") , type, null);
			XQResultSequence rs = exp.executeQuery();

			while (rs.next()) {
				result = (Element) rs.getObject();
				id = result.getAttributeNode("id").getValue();
				nombre = result.getAttributeNode("nombre").getValue();
				numPoderes = Integer.parseInt(result.getAttributeNode("num-ataques").getValue());
				
				Abedemon abedemon = new Abedemon(id, nombre, numPoderes);
				lista.add(abedemon);
			}

		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return lista;
    }

    /**
     * Obtiene la descripción de una especie de abedemon.
     * 
     * @param id ID de la especie a describir
     * @return Código XHTML con la descripción de la especie
     */
    @Override
    public String getAbedemonDescription(String id) {
    	InputStream is = getClass().getResourceAsStream("Abedemon3.xquery");
    	String resultado = null;

    	
    	try {
			XQConnection con = ds.getConnection();
			XQPreparedExpression exp = con.prepareExpression(is);
			exp.bindString(new QName("id") , id, null);
			XQResultSequence rs = exp.executeQuery();
			while (rs.next()) {
				resultado = rs.getItemAsString(null);
			}

		} catch (XQException e) {
			e.printStackTrace();
		}
    	
    	
    	return resultado;
    }

    /**
     * Obtiene el daño máximo recibido por un abedemon de una especie dada al
     * ser atacado por otro.
     * 
     * @param idAttacker ID de la especie del atacante.
     * @param idReceiver ID de la especie que recibe el daño.
     * @return Máximo daño que puede infligir el atacante.
     */
    @Override
    public Integer getDamage(String idAttacker, String idReceiver) {
    	InputStream is = getClass().getResourceAsStream("Abedemon4.xquery");
    	String resultado = "0";
    	
    	try {
			XQConnection con = ds.getConnection();
			XQPreparedExpression exp = con.prepareExpression(is);
			exp.bindString(new QName("yoId") , idAttacker, null);
			exp.bindString(new QName("adversarioId") , idReceiver, null);
			XQResultSequence rs = exp.executeQuery();
			while (rs.next()) {
				resultado = rs.getItemAsString(null);
			}

		} catch (XQException e) {
			e.printStackTrace();
		}
    	
    	
    	return Integer.parseInt(resultado);
    }
}
