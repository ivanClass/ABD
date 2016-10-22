package p1admin.adminDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.sql.DataSource;

public class DataAccessor {
	
	private DataSource ds;
	
	public DataAccessor(DataSource ds){
		this.ds = ds;
	}
	
	
	
	public int insertRow(String nombreTabla, String[] campos, Object[] values){
		
		int id;
		
		String sql = generateInsertStatement(nombreTabla, campos);
		
		try(Connection con = ds.getConnection();
				
			PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			for(int i = 0; i < values.length; i++){
				pst.setObject(i + 1, values[i]);
			}

			
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			
			return id;
		}
		catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean updateRow(String nombreTabla, String[] campos, Object[] nuevosValues, String[] keyColumnNames, Object[] keyValues){ //últimos dos parámetros en caso de WHERE
		
		int numFilas;
		
		String sql = generateUpdateStatement(nombreTabla, campos, keyColumnNames);
		
		try(Connection con = ds.getConnection();
				
			PreparedStatement pst = con.prepareStatement(sql)){
			
			for(int i = 0; i < campos.length; i++){  //insertamos los nombres de las columnas a editar
				pst.setObject(i + 1, nuevosValues[i]);
			}
			
			for(int i = 0; i < keyColumnNames.length; i++){  //insertamos los nombres de las columnas a buscar por el WHERE
				pst.setObject(campos.length + 1 + i, keyValues[i]);
			}
			
			numFilas = pst.executeUpdate();
			
			return (numFilas == 1);
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteRow(String nombreTabla,  String[] keyColumnNames, Object[] keyValues){ //últimos dos parámetros en caso de WHERE
		
		int numFilas;
		
		String sql = generateDeleteStatement(nombreTabla, keyColumnNames);
		
		try(Connection con = ds.getConnection();
				
			PreparedStatement pst = con.prepareStatement(sql)){
			
			for(int i = 0; i < keyColumnNames.length; i++){
				pst.setObject(i + 1, keyValues[i]);
			}

			numFilas = pst.executeUpdate();
			
			return (numFilas == 1);
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	private String generateInsertStatement(String nombreTabla, String[] campos) {

		String listaCampos = String.join(",", campos);
		String[] marks = new String[campos.length];
		Arrays.fill(marks, "?");
		String listaMarks = String.join(",", marks);
		
		return "INSERT INTO " + nombreTabla + " (" + listaCampos + ") VALUES (" + listaMarks + ")";
	}
	
	
	
	
	private String generateUpdateStatement(String nombreTabla, String[] campos, String[] columnaABuscar){

		String[] cosa = getColumnString(campos);
		String columnasIns = String.join(", ", cosa);
		
		String[] keyColumns = getColumnString(columnaABuscar);
		String keyColumnString = String.join(" AND ", keyColumns);
		
		return "UPDATE " + nombreTabla + " SET " +  columnasIns + " WHERE " + keyColumnString;
	}
	
	private String generateDeleteStatement(String nombreTabla, String[] keyColumnNames){

		
		String[] keyColumns = getColumnString(keyColumnNames);
		String keyColumnString = String.join(" AND ", keyColumns);
		
		return "DELETE FROM " + nombreTabla + " WHERE " + keyColumnString;
	}
	
	private String[] getColumnString(String[] columnsNames){
		String[] ret = new String[columnsNames.length];
		
		for(int i = 0; i < columnsNames.length; i++){
			ret[i] = columnsNames[i] + " = ?";
		}
		
		return ret;
	}
}
