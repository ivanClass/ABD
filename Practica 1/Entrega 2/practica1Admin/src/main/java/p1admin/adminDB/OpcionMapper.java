package p1admin.adminDB;


import javax.sql.DataSource;

import p1admin.model.Opcion;

public class OpcionMapper extends AbstractMapper<Opcion, Integer> {

	public OpcionMapper(DataSource ds) {
		super(ds);
	}

	@Override
	protected String getTableName() {
		return "respuestas";
	}

	@Override
	protected String[] getColumnNames() {
		return new String[]{"IdRespuesta", "numOrden", "Descripcion", "IdPregunta"};
	}

	@Override
	protected String[] getKeyColumnName() {
		return new String[]{"IdRespuesta","IdPregunta"};
	}

	@Override
	protected Object[] getValues(Opcion objeto) {
		return new Object[]{objeto.getIdOpcion(), objeto.getNumeroOrden(), objeto.getTexto(), objeto.getPreguntaMadre().getId()};
	}

	@Override
	protected Object[] getKeyValues(Opcion objeto) {
		return new Object[]{objeto.getIdOpcion(), objeto.getPreguntaMadre().getId()};
	}
}
