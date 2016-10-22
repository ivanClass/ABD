package p1admin.adminDB;

import javax.sql.DataSource;


public abstract class AbstractMapper<T, K> {
	protected DataAccessor dataAccessor;
	protected DataSource ds;

	public AbstractMapper(DataSource ds){
		this.ds = ds;
		this.dataAccessor = new DataAccessor(ds);
	}
	
	
	protected abstract String getTableName();
	protected abstract String[] getColumnNames();
	protected abstract String[] getKeyColumnName();
	protected abstract Object[] getValues(T objeto);
	protected abstract Object[] getKeyValues(T objeto);
	
	public int insert(T objeto){
		return dataAccessor.insertRow(getTableName(), getColumnNames(), getValues(objeto));
	}
	
	public boolean update(T objeto){
		return dataAccessor.updateRow(getTableName(), getColumnNames(), getValues(objeto), getKeyColumnName(), getKeyValues(objeto));
	}
	
	public boolean delete(T objeto){
		return dataAccessor.deleteRow(getTableName(), getKeyColumnName(), getKeyValues(objeto));
	}
}
